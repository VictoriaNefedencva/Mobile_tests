# Defects - my moldcell

## BUG-001: 'Login' button stays enabled when both fields are empty

**Environment:**
- Xiaomi, Android 14 (API 34)
- my moldcell, version 1.43.1 (Google Play)

**Preconditions:**
- App is open on the login screen

**Steps to reproduce:**
1. Leave both phone/email and password fields empty
2. Observe the 'Login' button state
3. Tap 'Login'

**Expected result:**
The button is disabled until both fields are filled.

**Actual result:**
The button remains enabled. Tapping it shows inline validation errors
(validation itself works, no login request is sent), but the button state
is misleading.

**Severity:** Minor
**Priority:** Medium
**Reproducibility:** 100%
**Evidence:**
- `manual/evidence/Bug_001.png` - Login button is active while both fields are empty.
- `manual/evidence/Log.csv` - PCAPdroid capture; no login request is sent when fields are empty.

---

## RISK-001: Login field may accept invalid phone/email formats

**Type:** Risk (not a confirmed defect - needs verification)

**Environment:**
- Xiaomi, Android 14 (API 34)
- my moldcell, version 1.43.1 (Google Play)

**Preconditions:**
- App is open on the login screen

**Steps to verify:**
1. Enter an invalid value in the login field (one at a time):
   - `123` (too short)
   - `abc` (letters only)
   - `test@` (incomplete email)
   - `+373` (incomplete phone)
   - emoji / special characters
2. Enter any password
3. Tap 'Login'
4. Observe: is a login request sent, or is an inline error shown before the request?

**Expected result:**
Invalid format is rejected client-side with an inline error.
No login request is sent for malformed input.

**Actual result:**
Not verified within the 3-hour limit. Only the "empty fields" case was tested
(works: no request is sent, inline errors are shown).

**Severity:** Unknown (potentially Major if requests are sent with malformed input)
**Priority:** Medium
**Reproducibility:** To be confirmed
**Evidence:** none yet - suggested capture via PCAPdroid to check if a login
request leaves the device for invalid formats.
