(ns change.view)

;; http://en.wikipedia.org/wiki/Coins_of_the_United_States_dollar

(def penny "<img alt='2010 cent obverse.png' src='//upload.wikimedia.org/wikipedia/commons/thumb/0/0c/2010_cent_obverse.png/48px-2010_cent_obverse.png' width='48' height='48' srcset='//upload.wikimedia.org/wikipedia/commons/thumb/0/0c/2010_cent_obverse.png/72px-2010_cent_obverse.png 1.5x, //upload.wikimedia.org/wikipedia/commons/thumb/0/0c/2010_cent_obverse.png/96px-2010_cent_obverse.png 2x'/>")

(def nickle "<img alt='Jefferson-Nickel-Unc-Obv.jpg' src='//upload.wikimedia.org/wikipedia/commons/thumb/7/72/Jefferson-Nickel-Unc-Obv.jpg/58px-Jefferson-Nickel-Unc-Obv.jpg' width='58' height='57' srcset='//upload.wikimedia.org/wikipedia/commons/thumb/7/72/Jefferson-Nickel-Unc-Obv.jpg/87px-Jefferson-Nickel-Unc-Obv.jpg 1.5x, //upload.wikimedia.org/wikipedia/commons/thumb/7/72/Jefferson-Nickel-Unc-Obv.jpg/116px-Jefferson-Nickel-Unc-Obv.jpg 2x'/>")

(def dime "<img alt='2005 Dime Obv Unc P.png' src='//upload.wikimedia.org/wikipedia/commons/thumb/1/17/2005_Dime_Obv_Unc_P.png/45px-2005_Dime_Obv_Unc_P.png' width='45' height='45' srcset='//upload.wikimedia.org/wikipedia/commons/thumb/1/17/2005_Dime_Obv_Unc_P.png/68px-2005_Dime_Obv_Unc_P.png 1.5x, //upload.wikimedia.org/wikipedia/commons/thumb/1/17/2005_Dime_Obv_Unc_P.png/90px-2005_Dime_Obv_Unc_P.png 2x'/>")

(def quarter "<img alt='Washington Quarter 79.PNG' src='//upload.wikimedia.org/wikipedia/commons/thumb/5/58/Washington_Quarter_79.PNG/60px-Washington_Quarter_79.PNG' width='60' height='60' srcset='//upload.wikimedia.org/wikipedia/commons/thumb/5/58/Washington_Quarter_79.PNG/90px-Washington_Quarter_79.PNG 1.5x, //upload.wikimedia.org/wikipedia/commons/thumb/5/58/Washington_Quarter_79.PNG/120px-Washington_Quarter_79.PNG 2x'/>")

(def coins { 1 penny 5 nickle 10 dime 25 quarter })

(defn view-form [amt]
  (str "
    <script type='text/javascript'>
    function make_change() {
      var loc = window.location,
          url = '/' + document.form.elements.amt.value;
      document.form.action = url;
      window.location = url;
    }
    </script>
    <form name='form' onsubmit='make_change(); return false;'>
      How much change do you want to make?
      <input name='amt' type='text' value='" amt "'/>
      <input type='submit' value='make change'/>
    </form>
    "))

(defn view-change [change]
  (let [amt (reduce (fn [sum key] (+ (* (get change key) key) sum)) 0 (keys change))]
    (str
      (view-form amt)
      (apply str
        (for [k (keys change)]
          (let [amount (get change k)
                coin (get coins k)]
            (apply (partial str "<br/>") (repeat amount coin))))))))

