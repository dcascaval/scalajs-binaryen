package example

import scalajs.js
import js.annotation.*
import org.scalajs.dom.console
import scala.scalajs.js.typedarray.Uint8Array

@js.native
@JSGlobal
object binaryen extends js.Object:

  //
  // TYPES
  //
  @js.native
  trait Type extends js.Object
  @js.native
  object none extends Type
  @js.native
  object unreachable extends Type
  @js.native
  object auto extends Type
  @js.native
  object i32 extends Type
  @js.native
  object i64 extends Type
  @js.native
  object f32 extends Type
  @js.native
  object f64 extends Type
  def createType(types: js.Array[Type]): Type = js.native
  def expandType(types: Type): js.Array[Type] = js.native

  @js.native
  trait ExpressionRef extends js.Object

  def parseText(text: String): Module = js.native
  def readBinary(data: Uint8Array): Module = js.native

  @js.native
  class Module() extends js.Object:

    @js.native
    object local extends js.Object:
      def get(index: Int, typ: Type): ExpressionRef = js.native

    @js.native
    object i32 extends js.Object:
      def add(a: ExpressionRef, b: ExpressionRef): ExpressionRef = js.native

    @JSName("return")
    def ret(code: ExpressionRef): ExpressionRef = js.native

    def addFunction(
        name: String,
        in: Type,
        out: Type,
        locals: js.Array[Type],
        ret: ExpressionRef
    ): Unit =
      js.native
    def addFunctionExport(name: String, exportName: String): Unit = js.native

    def emitText(): String = js.native
    def optimize(): Unit = js.native
    def emitBinary(): Uint8Array = js.native
    def validate(): Int = js.native
    def dispose(): Unit = js.native

@js.native
@JSGlobal
object WebAssembly extends js.Object {
  @js.native
  class Module(binary: Uint8Array) extends js.Object
  @js.native
  class Instance(module: Module, options: js.Object) extends js.Object:
    val exports: js.Object = js.native
}

// Take it for a spin
def demo() =
  val module = new binaryen.Module()

  val left = module.local.get(0, binaryen.i32)
  val right = module.local.get(1, binaryen.i32)
  val add = module.i32.add(left, right)
  val ret = module.ret(add)

  var ii = binaryen.createType(js.Array(binaryen.i32, binaryen.i32))
  module.addFunction("adder", ii, binaryen.i32, js.Array(), ret)

  // Export the function, so we can call it later (for simplicity we
  // export it as the same name as it has internally)
  module.addFunctionExport("adder", "adder")

  console.log(module.emitText())

  // Optimize the module! This removes the 'return', since the
  // output of the add can just fall through
  module.optimize()

  // Print out the optimized module's text
  console.log(s"optimized:\n\n${module.emitText()}")

  // Get the binary in typed array form
  val binary = module.emitBinary();
  console.log(s"binary size: ${binary.length}")
  println(" ")
  assert(module.validate() == 1)

  // We don't need the Binaryen module anymore, so we can tell it to
  // clean itself up
  module.dispose()

  // Compile the binary and create an instance
  val wasm =
    new WebAssembly.Instance(new WebAssembly.Module(binary), js.Object())
  console.log(s"exports: ${js.Object.keys(wasm.exports).sort().join(",")}")
  println(" ")

  // Call the code!
  console.log(s"splt: ${wasm.exports
    .asInstanceOf[js.Dynamic]
    .adder(Seq(40, 2): _*)}");
