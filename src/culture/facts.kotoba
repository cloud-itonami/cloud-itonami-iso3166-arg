(ns culture.facts
  "Country-level regional-culture catalog for Argentina (ARG) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `marketentry.facts` / `statute.facts` (ADR-2607141700); city-level
  counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"ARG"
   [{:culture/id "arg.dish.asado"
     :culture/name "Asado"
     :culture/country "ARG"
     :culture/kind :dish
     :culture/summary "Barbecue technique and social event of various South American countries, especially Argentina and Uruguay, where it is a traditional event."
     :culture/url "https://en.wikipedia.org/wiki/Asado"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "arg.dish.milanesa"
     :culture/name "Milanesa"
     :culture/country "ARG"
     :culture/kind :dish
     :culture/summary "Breaded cutlet dish of Latin American cuisine, closely tied to the cuisine of Argentina where it is considered a quintessential national dish."
     :culture/url "https://en.wikipedia.org/wiki/Milanesa"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "arg.dish.locro"
     :culture/name "Locro"
     :culture/country "ARG"
     :culture/kind :dish
     :culture/summary "Hearty squash, corn and potato stew of Andean origin, one of the national dishes of several Andean countries and Northwest Argentina, traditionally eaten in Argentina on the patriotic dates of May 25 and July 9."
     :culture/url "https://en.wikipedia.org/wiki/Locro"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "arg.dish.alfajor"
     :culture/name "Alfajor"
     :culture/country "ARG"
     :culture/kind :dish
     :culture/summary "Sweet confection of two round biscuits joined with dulce de leche, originating in medieval Spain; Argentina is one of the world's largest consumers, eating more than a billion alfajores a year."
     :culture/url "https://en.wikipedia.org/wiki/Alfajor"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "arg.beverage.mate"
     :culture/name "Mate"
     :culture/country "ARG"
     :culture/kind :beverage
     :culture/summary "Caffeine-rich infused herbal drink made with yerba mate, the national beverage of Argentina, Paraguay and Uruguay."
     :culture/url "https://en.wikipedia.org/wiki/Mate_(drink)"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "arg.beverage.malbec"
     :culture/name "Malbec"
     :culture/country "ARG"
     :culture/kind :beverage
     :culture/summary "Purple grape variety originally from France that became the most widely planted red grape of Argentine wine, uniquely identified with Argentina and centred on Mendoza."
     :culture/url "https://en.wikipedia.org/wiki/Malbec"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "arg.product.dulce-de-leche"
     :culture/name "Dulce de leche"
     :culture/country "ARG"
     :culture/kind :product
     :culture/summary "Caramelized milk-and-sugar confection popular across Latin America with disputed origins; Argentina sought to declare it intangible cultural heritage in 2003, over objections from neighbouring countries."
     :culture/url "https://en.wikipedia.org/wiki/Dulce_de_leche"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "arg.festival.fiesta-nacional-de-la-vendimia"
     :culture/name "Fiesta Nacional de la Vendimia"
     :culture/country "ARG"
     :culture/kind :festival
     :culture/summary "Annual grape harvest festival held in Mendoza City, one of the most important festivals in Argentina."
     :culture/url "https://en.wikipedia.org/wiki/Fiesta_Nacional_de_la_Vendimia"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "arg.heritage.iguazu-national-park"
     :culture/name "Iguazú National Park"
     :culture/name-local "Parque Nacional Iguazú"
     :culture/country "ARG"
     :culture/kind :heritage
     :culture/summary "National park in Misiones Province, Argentina, protecting the Iguazu Falls, designated a UNESCO World Heritage Site in 1984."
     :culture/url "https://en.wikipedia.org/wiki/Iguaz%C3%BA_National_Park"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-arg culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "ARG"))
                 " ARG entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
