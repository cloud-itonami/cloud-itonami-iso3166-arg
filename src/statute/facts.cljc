(ns statute.facts
  "General-law compliance catalog for Argentina (ARG) -- extends this
  repo's existing `marketentry.facts` (public-procurement market-entry
  only, narrow scope) with a second, orthogonal catalog of statutes a
  company generally must track for compliance. Mirrors
  cloud-itonami-iso3166-jpn/-usa/-esp/-swe/-nor/-dnk/-fin/-prt/-bel/-bra/-mex/-chl's
  `statute.facts` (ADR-2607141700, cloud-itonami-compliance-fact-federation).

  Every entry cites an OFFICIAL servicios.infoleg.gob.ar (InfoLeg,
  Argentina's Ministry of Justice and Human Rights official legislative
  information system) URL -- never fabricated. A law not in this table
  has NO spec-basis, full stop; extend `catalog`, do not invent an
  id/url. infoleg.gob.ar rendered directly to WebFetch (unlike
  bcn.cl/leychile.cl's JS-only pages for Chile).")

(def catalog
  "iso3 -> vector of statute entries."
  {"ARG"
   [{:statute/id "arg.ley-general-de-sociedades-19550"
     :statute/title "Ley General de Sociedades N° 19.550 (Ley de Sociedades Comerciales)"
     :statute/jurisdiction "ARG"
     :statute/kind :law
     :statute/law-number "Ley N° 19.550"
     :statute/url "https://servicios.infoleg.gob.ar/infolegInternet/anexos/25000-29999/25553/texact.htm"
     :statute/url-provenance :official-infoleg-gob-ar
     :statute/enacted-date "1972-04-03"
     :statute/last-revised-date "2018-06-18"
     :statute/retrieved-at "2026-07-15"
     :statute/topic #{:corporate-governance :incorporation}}
    {:statute/id "arg.ley-habeas-data-25326"
     :statute/title "Ley N° 25.326 (Protección de los Datos Personales / Habeas Data)"
     :statute/jurisdiction "ARG"
     :statute/kind :law
     :statute/law-number "Ley N° 25.326"
     :statute/url "https://servicios.infoleg.gob.ar/infolegInternet/verNorma.do?id=64790"
     :statute/url-provenance :official-infoleg-gob-ar
     :statute/enacted-date "2000-10-04"
     :statute/retrieved-at "2026-07-15"
     :statute/topic #{:data-protection :privacy}}
    {:statute/id "arg.ley-contrato-trabajo-20744"
     :statute/title "Ley N° 20.744 (Ley de Contrato de Trabajo)"
     :statute/jurisdiction "ARG"
     :statute/kind :law
     :statute/law-number "Ley N° 20.744"
     :statute/url "https://servicios.infoleg.gob.ar/infolegInternet/verNorma.do?id=25552"
     :statute/url-provenance :official-infoleg-gob-ar
     :statute/enacted-date "1974-09-05"
     :statute/retrieved-at "2026-07-15"
     :statute/topic #{:labor :employment}}]})

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
      :note (str "cloud-itonami-iso3166-arg statute.facts Wave 0 (ADR-2607141700): "
                 (count (get catalog "ARG")) " ARG statutes seeded with an "
                 "official infoleg.gob.ar citation. Extend "
                 "`statute.facts/catalog`, never fabricate a law-id or URL.")})))

(defn by-topic [iso3 topic]
  (filterv #(contains? (:statute/topic %) topic) (spec-basis iso3)))
