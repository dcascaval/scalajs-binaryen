function Dc() {
  this.Gb = k;
  this.Qa = this.Hb = null;
  Ec = this; rc(this);
  Fc();
  // Ta() = document
  Ta().addEventListener("DOMContentLoaded",
    (() => () => {
      Aa || (Aa = new za);
      var a = 0; // item = 0 
      var b = +Sa(Ua()).innerHeight; // b = window.innerHeight
      // .toInt() conversion
      var c = 2147483647 < b ? 2147483647 : -2147483648 > b ? -2147483648 : b | 0;
      b = -1 + c | 0;

      // Don't start the loop if upper bound < 0 
      if (!(0 >= c))
        // i = 0
        for (c = 0; ;) {
          var d = c;
          // item += (i * i)
          a = a + f(d, d) | 0;
          if (c === b) break;
          // i++
          c = 1 + c | 0
        }

      b = Ta().body;
      c = Ta().createElement("p");
      c.innerHTML = "" + a;
      return b.appendChild(c)
    })(this))
}


// Take 2: force a seq conversion. 
// this seemingly gets inlined out.

function Dc() {
  this.Gb = k; this.Qa = this.Hb = null; Ec = this; rc(this); Fc();
  Ta().addEventListener("DOMContentLoaded",
    (() => () => {
      Aa || (Aa = new za);
      var a = Ta();
      var b = 0;
      var c = +Sa(Ua()).innerHeight;
      var d = 2147483647 < c ? 2147483647 : -2147483648 > c ? -2147483648 : c | 0;
      c = -1 + d | 0;
      if (!(0 >= d))
        for (d = 0; ;) {
          var f = d;
          b = b + g(f, f) | 0;
          if (d === c) break;
          d = 1 + d | 0
        }
      c = a.body;
      a = a.createElement("p");
      a.innerHTML = "" + b;
      return c.appendChild(a)
    })(this))
} Dc.prototype = new u; Dc.prototype.constructor = Dc;


// `document`
function Ta() {
  var a = Ua();
  // This looks like it's some sort of bitfield, it's 0x1 << 26. 
  // check is still duplicated though
  0 === (67108864 & a.w) && 0 === (67108864 & a.w) &&
    (a.Ra = Sa(a).document, a.w |= 67108864);
  return a.Ra
}

// Some abstraction for global `this` -- 
// w is an access bitfield, Ra and Sa are both null to start with
// but later become document and window.
var Va;
function Ra() { this.Ra = this.Sa = null; this.w = 0 }
Ra.prototype = new u; Ra.prototype.constructor = Ra;
function Ua() { Va || (Va = new Ra); return Va }

// `window`
function Sa(a) {
  0 === (33554432 & a.w) && 0 === (33554432 & a.w)
    && (a.Sa = window, a.w |= 33554432);
  return a.Sa
}



Ra.prototype.$classData = y({ Lb: 0 }, "org.scalajs.dom.package$", { Lb: 1, a: 1 }); var Va; function Ua() { Va || (Va = new Ra); return Va } function Wa(a, b, c, d, f) { var h = b.t; 0 !== (c.length | 0) && (h.e = "" + h.e + c); a = a.j(); if (a.f()) for (c = a.h(), h.e = "" + h.e + c; a.f();)h.e = "" + h.e + d, c = a.h(), h.e = "" + h.e + c; 0 !== (f.length | 0) && (h.e = "" + h.e + f); return b }
