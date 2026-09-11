(ns statute.facts
  "General-law compliance catalog for North Macedonia (MKD) -- extends
  this repo's existing `marketentry.facts` (public-procurement market-
  entry only, narrow scope) with a second, orthogonal catalog of
  statutes a company operating in this jurisdiction must generally track
  for compliance. Mirrors cloud-itonami-iso3166-bih/-alb/-bgr's
  `statute.facts` (ADR-2607141700, cloud-itonami-compliance-fact-
  federation).

  North Macedonia is a UNITARY state (no Entity/regional split the way
  this family's Bosnia and Herzegovina sibling models) -- every entry
  below applies nationwide; no `:statute/level` field is needed (that
  field is specific to a federated jurisdiction and this catalog
  deliberately omits it, matching this family's Albania sibling's own
  unitary-state precedent).

  Every entry cites an OFFICIAL government-hosted URL -- never
  fabricated. All five entries below were fetched directly this session
  (2026-07-22/23) and read via `pdftotext -layout` on the downloaded
  PDF, not taken from a secondary summary:

  - Закон за работните односи (Labour Relations Law) -- base text
    'Службен весник на Република Македонија' бр. 62/2005. This
    session's own PDF fetch (from portal.mdt.gov.mk, the Ministry of
    Economy and Labour's official document portal) turned out to be an
    Official Gazette issue (бр. 39, 20 February 2025) containing a LATER
    amending law, whose own Albanian-language operative text (North
    Macedonia's Official Gazette publishes bilingual MK/SQ for laws
    amending certain acts) independently enumerates the FULL prior
    amendment chain as its own legal basis: '(\"Gazeta Zyrtare e
    Republikës së Maqedonisë\" numër 62/2005, 3/2006, 44/2006, 66/2006,
    16/2007, 57/2007, 77/2007, 106/2008, 161/2008, 63/2009, 114/2009,
    130/2009, 149/2009, 10/10, 50/10, 52/10, 58/10, 124/10, 132/10,
    47/11, 11/12, 39/12, 13/13, 25/13, 170/13, 187/13, 106/14, 113/14,
    20/15, 33/15, 72/15, 129/15, 27/16, 134/16 dhe 120/18 dhe \"Gazeta
    Zyrtare e Republikës së Maqedonisë së Veriut\" numër 110/19, 267/20,
    151/21, 288/21 dhe 111/23)' -- i.e. base 62/2005 through amendment
    111/23, PLUS this Feb-2025 gazette issue's own amendment (a
    ministry-name-only technical amendment, replacing 'Министерство за
    труд и социјална политика' with 'Министерство за социјална
    политика, демографија и млади' throughout, reflecting a real 2025
    machinery-of-government restructuring this session independently
    observed: economy.gov.mk now reads 'Министерство за економија и
    труд' -- labour moved INTO the economy ministry -- while
    mtsp.gov.mk now reads 'Министерство за социјална политика,
    демографија и млади'). Confidence: HIGH for the amendment-chain
    enumeration (directly read from the government's own gazette PDF,
    which cites its own predecessor chain); this session did NOT
    separately fetch and read each of the ~35 individual amendment
    texts, only the base identification and the chain as the
    government's own document states it.
  - Закон за трговските друштва (Law on Trade Companies) -- base
    'Службен весник на РМ' бр.28/04, редакциски пречистен текст
    (consolidated text) whose own footnote states it covers amendments
    through 'Службен весник на РСМ' бр.290/20 and бр.215/21. Fetched
    directly (portal.mdt.gov.mk) and read in full (283 pages,
    `pdftotext -layout`). This is ALSO `marketentry.facts`'s
    `registration-legal-basis` citation (ЕМБС / Central Registry) --
    included here too because it is also the general company-law
    statute a company must track for compliance beyond procurement
    (Член 1 lists: trader forms, share capital, shareholder rights,
    annual accounts, mergers/divisions, liquidation, foreign companies,
    branches, penalties). Amendments after бр.215/21 (2021) were NOT
    independently fetched/verified this iteration -- an honest,
    disclosed gap, not a claim of full 2026 currency.
  - Закон за данокот на добивка (Profit Tax Law) -- fetched directly
    from Управа за јавни приходи's (Public Revenue Office, УЈП) own
    regulatory catalog (ujp.gov.mk/mk/regulativa/opis/295), which states
    its own citation verbatim: 'Објава: „Службен весник на РМ“, број
    112/14...248/18 и Службен весник на РСМ“, број 232/19…199/23. Важи
    од: 25.09.2023' (in effect from 2025-09-25 -- УЈП's own site
    abbreviates the middle of both amendment ranges with an ellipsis;
    this session cites the chain exactly as УЈП's own site states it,
    not a fabricated full enumeration of every intermediate gazette
    number).
  - Закон за финансиска поддршка на инвестициите (Law on Financial
    Support of Investments) -- the FOREIGN-investment-relevant statute
    this session specifically investigated (rather than assuming Trade
    Companies Law's foreign-shareholder provisions alone constitute a
    dedicated 'foreign investment law'): fetched directly
    (portal.mdt.gov.mk) and read in full (16 pages). Its own text
    ('Службен весник на РМ, бр. 83 од 8.5.2018 година', adopted by the
    Assembly 2018-05-03) explicitly involves the 'Агенција за странски
    инвестиции и промоција на извозот на Република Македонија' (Agency
    for Foreign Investments and Export Promotion) and 'Министри без
    ресор задолжени за странски инвестиции' (Ministers without
    portfolio in charge of foreign investments) among the bodies
    administering the financial-support scheme -- confirming this is
    genuinely foreign-investment-relevant, not merely a generic
    business-subsidy law. This session fetched and read ONLY the 2018
    base text; a later 2019 amendment (бр.98/19, referenced on
    economy.gov.mk's own legislation listing) was NOT independently
    fetched/read this iteration -- an honest, disclosed gap.
  - Закон за данокот на додадена вредност (VAT Law) -- fetched directly
    from УЈП's own regulatory catalog (ujp.gov.mk/mk/regulativa/opis/17),
    which states its own citation verbatim: 'Објава: : \"Службен весник
    на РМ\", бр. 44/99....198/18 и \"Службен весник на РСМ\", бр.98/19
    ......267/25. Важи од: 30.12.2025' -- again cited exactly as УЈП's
    own site abbreviates the chain, not reconstructed independently.

  A law not in this table has NO spec-basis, full stop; extend
  `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of statute entries. `:statute/url` + `:statute/law-number`
  are the citation the governor requires before any compliance-fact
  proposal referencing this law can commit."
  {"MKD"
   [{:statute/id "mkd.labour-relations-law"
     :statute/title "Закон за работните односи (Labour Relations Law)"
     :statute/jurisdiction "MKD"
     :statute/kind :law
     :statute/law-number "Службен весник на Република Македонија бр. 62/2005, со измени и дополнувања низ бр. 3/2006...120/18 и \"Службен весник на Република Северна Македонија\" бр. 110/19, 267/20, 151/21, 288/21, 111/23 и понатамошна техничка измена во бр. 39/25 (chain as independently enumerated inside the amending law's own text, not reconstructed)"
     :statute/url "https://portal.mdt.gov.mk/post-body-files/zakoni-met-file-Jv8C.pdf"
     :statute/url-provenance :official-ministry-of-economy-and-labour
     :statute/enacted-date "2005-01-01"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:labor :employment}}
    {:statute/id "mkd.trade-companies-law"
     :statute/title "Закон за трговските друштва (Law on Trade Companies)"
     :statute/jurisdiction "MKD"
     :statute/kind :law
     :statute/law-number "Службен весник на РМ бр.28/04, редакциски пречистен текст низ Службен весник на РСМ бр.290/20 и бр.215/21 (later amendments not independently verified this iteration)"
     :statute/url "https://portal.mdt.gov.mk/post-body-files/zakoni-met-file-EEmd.pdf"
     :statute/url-provenance :official-ministry-of-economy-and-labour
     :statute/enacted-date "2004-01-01"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:corporate-governance :incorporation}}
    {:statute/id "mkd.profit-tax-law"
     :statute/title "Закон за данокот на добивка (Profit Tax Law)"
     :statute/jurisdiction "MKD"
     :statute/kind :law
     :statute/law-number "Службен весник на РМ бр.112/14...248/18 и Службен весник на РСМ бр.232/19...199/23 (chain abbreviated exactly as УЈП's own regulatory catalog states it); важи од 25.09.2023"
     :statute/url "https://www.ujp.gov.mk/mk/regulativa/opis/295"
     :statute/url-provenance :official-public-revenue-office
     :statute/enacted-date "2023-09-25"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:tax}}
    {:statute/id "mkd.investment-support-law"
     :statute/title "Закон за финансиска поддршка на инвестициите (Law on Financial Support of Investments)"
     :statute/jurisdiction "MKD"
     :statute/kind :law
     :statute/law-number "Службен весник на РМ бр.83 од 8.5.2018 (base text only -- a later amendment, бр.98/19 per economy.gov.mk's own legislation listing, not independently fetched/read this iteration)"
     :statute/url "https://portal.mdt.gov.mk/post-body-files/zakoni-met-file-7sZP.pdf"
     :statute/url-provenance :official-ministry-of-economy-and-labour
     :statute/enacted-date "2018-05-08"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:foreign-investment}}
    {:statute/id "mkd.vat-law"
     :statute/title "Закон за данокот на додадена вредност (VAT Law)"
     :statute/jurisdiction "MKD"
     :statute/kind :law
     :statute/law-number "Службен весник на РМ бр.44/99...198/18 и Службен весник на РСМ бр.98/19...267/25 (chain abbreviated exactly as УЈП's own regulatory catalog states it); важи од 30.12.2025"
     :statute/url "https://www.ujp.gov.mk/mk/regulativa/opis/17"
     :statute/url-provenance :official-public-revenue-office
     :statute/enacted-date "2025-12-30"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:tax}}]})

(defn spec-basis
  "The jurisdiction's statute vector, or nil -- nil means NO spec-basis
  for that jurisdiction yet."
  [iso3]
  (get catalog iso3))

(defn coverage
  "Honest coverage report, same shape/discipline as `marketentry.facts/coverage`:
  never report a missing jurisdiction as covered."
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-mkd statute.facts Wave 0 (ADR-2607141700): "
                 (count (get catalog "MKD")) " MKD statutes seeded with an "
                 "official government-hosted citation. Extend "
                 "`statute.facts/catalog`, never fabricate a law-id or URL.")})))

(defn by-topic
  "Statutes for `iso3` tagged with `topic` (e.g. :labor, :tax)."
  [iso3 topic]
  (filterv #(contains? (:statute/topic %) topic) (spec-basis iso3)))
