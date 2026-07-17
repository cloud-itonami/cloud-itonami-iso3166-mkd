(ns culture.facts
  "Country-level regional-culture catalog for North Macedonia (MKD) --
  national dishes, protected products, beverages, crafts, festivals and
  heritage sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
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
  {"MKD"
   [{:culture/id "mkd.dish.tavce-gravce"
     :culture/name "Tavče gravče"
     :culture/country "MKD"
     :culture/kind :dish
     :culture/summary "Traditional dish of butter beans baked in an earthenware pot, recognized as the national dish of North Macedonia."
     :culture/url "https://en.wikipedia.org/wiki/Tav%C4%8De_grav%C4%8De"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "mkd.dish.pastrmajlija"
     :culture/name "Pastrmajlija"
     :culture/country "MKD"
     :culture/kind :dish
     :culture/summary "Macedonian bread pie made from dough, cheese, eggs and meat, originating in and remaining popular in cities such as Štip, Veles and Sveti Nikole."
     :culture/url "https://en.wikipedia.org/wiki/Pastrmajlija"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "mkd.dish.ajvar"
     :culture/name "Ajvar"
     :culture/country "MKD"
     :culture/kind :dish
     :culture/summary "Condiment of sweet bell peppers and eggplant produced in most Balkan countries including North Macedonia; \"Macedonian Ajvar\" is registered with WIPO as a distinct brand name."
     :culture/url "https://en.wikipedia.org/wiki/Ajvar"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "mkd.beverage.macedonian-wine"
     :culture/name "Macedonian wine"
     :culture/country "MKD"
     :culture/kind :beverage
     :culture/summary "Wine produced across roughly 22,400 hectares of vineyards in North Macedonia, with red wine comprising about 80 percent of output from three distinct wine regions."
     :culture/url "https://en.wikipedia.org/wiki/Macedonian_wine"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "mkd.product.prilep-tobacco"
     :culture/name "Prilep tobacco"
     :culture/country "MKD"
     :culture/kind :product
     :culture/summary "Prilep is a regional centre for high-quality oriental tobacco, a traditional cash crop of the city used by major cigarette makers after local processing."
     :culture/url "https://en.wikipedia.org/wiki/Prilep"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "mkd.festival.galicnik-wedding"
     :culture/name "Galičnik Wedding Festival"
     :culture/country "MKD"
     :culture/kind :festival
     :culture/summary "Annual cultural festival in the village of Galičnik near Debar, where selected couples marry in traditional style, historically held around St. Peter and Paul's Day."
     :culture/url "https://en.wikipedia.org/wiki/Gali%C4%8Dnik_Wedding_Festival"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "mkd.heritage.ohrid"
     :culture/name "Ohrid"
     :culture/country "MKD"
     :culture/kind :heritage
     :culture/summary "Ohrid and Lake Ohrid were inscribed as UNESCO Cultural and Natural World Heritage Sites in 1979 and 1980 respectively."
     :culture/url "https://en.wikipedia.org/wiki/Ohrid"
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
      :note (str "cloud-itonami-iso3166-mkd culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "MKD"))
                 " MKD entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
