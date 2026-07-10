(ns marketentry.facts "Argentina market-entry catalog.")
(def catalog
  {"ARG" {:name "Argentine Republic"
          :owner-authority "ONC / COMPR.AR"
          :legal-basis "Régimen de Contrataciones del Estado"
          :national-spec "COMPR.AR / SECOP-style portals + CUIT"
          :provenance "https://comprar.gob.ar/"
          :required-evidence ["CUIT record" "COMPR.AR registration record" "IGJ/AFIP extract" "Authorized-representative record"]
          :rep-owner-authority "contracting authorities / ONC"
          :rep-legal-basis "Argentine legal entity (CUIT) typically required for federal awards"
          :rep-provenance "https://comprar.gob.ar/"
          :corporate-number-owner-authority "AFIP / IGJ"
          :corporate-number-legal-basis "CUIT / CUIL"
          :corporate-number-provenance "https://www.afip.gob.ar/"}
   "USA" {:name "United States" :owner-authority "GSA/SAM.gov" :legal-basis "FAR" :national-spec "SAM.gov" :provenance "https://sam.gov/"
          :required-evidence ["EIN record" "SAM.gov registration record" "State business registration record" "SAM UEI verification record"]}
   "BRA" {:name "Brazil" :owner-authority "Compras.gov.br" :legal-basis "Lei 14.133/2021" :national-spec "Compras.gov.br" :provenance "https://www.gov.br/compras/"
          :required-evidence ["CNPJ record" "Compras.gov.br registration" "SICAF record" "Authorized-representative record"]}
   "CHL" {:name "Chile" :owner-authority "ChileCompra" :legal-basis "Ley de Compras" :national-spec "Mercado Público" :provenance "https://www.mercadopublico.cl/"
          :required-evidence ["RUT record" "ChileCompra registration" "SII extract" "Authorized-representative record"]}})

(defn spec-basis [iso3] (get catalog iso3))
(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s) missing (remove catalog iso3s)]
     {:requested (count iso3s) :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note "R0 catalog seed"})))
(defn required-evidence-satisfied? [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (= (count required-evidence) (count (filter (set submitted) required-evidence)))))
(defn evidence-checklist [iso3] (:required-evidence (spec-basis iso3) []))
(defn rep-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:rep-owner-authority sb)
      (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))
(defn corporate-number-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority :corporate-number-legal-basis :corporate-number-provenance]))))
