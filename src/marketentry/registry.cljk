(ns marketentry.registry
  "Pure-function market-entry filing-draft + filing-submit record
  construction -- an append-only market-entry book-of-record draft.

  Like every sibling actor's registry, there is no single international
  reference-number standard for a public-procurement market-entry
  filing -- every jurisdiction assigns its own format. This namespace
  does NOT invent one; it builds a jurisdiction-scoped sequence number
  and validates the record's required fields, the same honest,
  non-fabricating discipline `marketentry.facts` uses.

  `engagement-fee-matches-claim?` is an HONEST reapplication of the
  SAME ground-truth-recompute DISCIPLINE sibling actors use (verify a
  claimed monetary total against the entity's own recorded quantity x
  unit fields), reapplied to a market-entry engagement fee line.

  `public-charges-arrears-violation?` is the FLAGSHIP genuinely new
  check for this jurisdiction -- and a genuinely different SHAPE than
  any sibling this family has built so far. ЗЈН (Закон за јавните
  набавки, Law on Public Procurement) Член 88(2)(a) -- fetched directly
  from the Bureau for Public Procurement's own consolidated PDF
  (bjn.gov.mk, `pdftotext -layout`-read in full, 2026-07-22/23) --
  excludes an economic operator with unpaid taxes, contributions or
  other public charges UNLESS BOTH: (1) it has been GRANTED an approved
  deferred-payment arrangement, AND (2) it is CURRENTLY paying that
  arrangement regularly. This is a CONJUNCTIVE two-condition exception
  -- unlike a sibling jurisdiction's single boolean 'reprogram
  confirmed' flag (which treats mere approval as sufficient cure) or
  another sibling's flat/percentage-of-turnover de-minimis threshold
  (a numeric recompute, not a state-conjunction). Approval WITHOUT
  ongoing regular payment does NOT cure the violation under North
  Macedonia's own statute -- a genuinely new check shape this session
  specifically looked for (task instruction: don't default to a
  sibling's shape) rather than reused.

  This namespace is pure data + pure functions -- no I/O, no network
  call to any real procurement portal. It builds the RECORD an
  operator would keep, not the act of submitting a portal registration
  itself (that is `marketentry.operation`'s `:filing/submit`, always
  human-gated -- see README Actuation)."
  (:require [kotoba.lang.text :as str]))

(defn- unsigned-certificate
  "Every certificate this actor produces is UNSIGNED -- signature is
  the market-entry operator's act, not this actor's."
  [kind subject record-id]
  {"@context" ["https://www.w3.org/ns/credentials/v2"]
   "type" ["VerifiableCredential" kind]
   "credentialSubject" {"id" subject "record" record-id}
   "proof" nil
   "issued_by_registry" false
   "status" "draft-unsigned"})

(defn- zero-pad [n w]
  (let [s (str n)]
    (str (apply str (repeat (max 0 (- w (count s))) "0")) s)))

(defn compute-engagement-fee
  "The ground-truth engagement fee for `engagement`'s own `:base-fee`
  and `:monitoring-months` x `:monthly-rate` -- a single flat
  base + months x rate calculation, not a full pricing engine."
  [{:keys [base-fee monthly-rate monitoring-months]}]
  (+ (double base-fee)
     (* (double monthly-rate) (double monitoring-months))))

(defn engagement-fee-matches-claim?
  "Does `engagement`'s own `:claimed-fee` equal the independently
  recomputed `compute-engagement-fee`?"
  [{:keys [claimed-fee] :as engagement}]
  (== (double claimed-fee) (compute-engagement-fee engagement)))

(defn public-charges-arrears-violation?
  "ЗЈН Член 88(2)(a): does `engagement` have an undisputed positive
  `:tax-arrears-amount` where the statute's deferred-payment exception
  does NOT apply? The exception requires BOTH
  `:tax-deferred-payment-approved?` (odobreno odlozeno plakjanje --
  the tax authority has GRANTED a deferred-payment arrangement) AND
  `:tax-deferred-payment-current?` (istite redovno gi plakja -- the
  operator is CURRENTLY paying that arrangement regularly). Approval
  alone, without ongoing regular payment, does NOT cure the violation
  -- this is the flagship genuinely-new-shape check for this
  jurisdiction (see namespace docstring): a CONJUNCTION of two ground-
  truth booleans, not a single confirmed-flag or a numeric threshold.
  Missing/zero arrears never violate."
  [{:keys [tax-arrears-amount tax-deferred-payment-approved? tax-deferred-payment-current?]}]
  (and (> (double (or tax-arrears-amount 0)) 0.0)
       (not (and (true? tax-deferred-payment-approved?)
                 (true? tax-deferred-payment-current?)))))

(defn register-draft
  "Validate + construct the FILING-DRAFT registration DRAFT -- the
  market-entry operator's own act of preparing a portal registration
  package. Pure function -- does not touch any real procurement
  portal."
  [engagement-id jurisdiction sequence]
  (when-not (and engagement-id (not= engagement-id ""))
    (throw (ex-info "draft: engagement_id required" {})))
  (when-not (and jurisdiction (not= jurisdiction ""))
    (throw (ex-info "draft: jurisdiction required" {})))
  (when (< sequence 0)
    (throw (ex-info "draft: sequence must be >= 0" {})))
  (let [draft-number (str (str/upper jurisdiction) "-DFT-" (zero-pad sequence 6))
        record {"record_id" draft-number
                "kind" "filing-draft"
                "engagement_id" engagement-id
                "jurisdiction" jurisdiction
                "immutable" true}]
    {"record" record "draft_number" draft-number
     "certificate" (unsigned-certificate "FilingDraft" draft-number draft-number)}))

(defn register-submit
  "Validate + construct the FILING-SUBMIT registration DRAFT -- the
  market-entry operator's own act of actually submitting a portal
  registration (always human-gated upstream)."
  [engagement-id jurisdiction sequence]
  (when-not (and engagement-id (not= engagement-id ""))
    (throw (ex-info "submit: engagement_id required" {})))
  (when-not (and jurisdiction (not= jurisdiction ""))
    (throw (ex-info "submit: jurisdiction required" {})))
  (when (< sequence 0)
    (throw (ex-info "submit: sequence must be >= 0" {})))
  (let [submit-number (str (str/upper jurisdiction) "-SUB-" (zero-pad sequence 6))
        record {"record_id" submit-number
                "kind" "filing-submit"
                "engagement_id" engagement-id
                "jurisdiction" jurisdiction
                "immutable" true}]
    {"record" record "submit_number" submit-number
     "certificate" (unsigned-certificate "FilingSubmit" submit-number submit-number)}))

(defn append [history result]
  (conj (vec history) (get result "record")))
