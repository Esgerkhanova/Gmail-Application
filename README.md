 Gmail + Translate Clone

A Spring Boot application that combines **basic Gmail-like features** with a **simple Google Translate clone**.  
Includes email inbox, starred mails, compose functionality, user settings, and a toy dictionary-based translation engine.

---

 What it Does

- **Email Features**
  - Inbox, Starred, Sent folders
  - Compose new mails
  - Star/unstar mails
  - Demo data with preloaded inbox messages

- **Translate Features**
  - Translate text between English (EN), Azerbaijani (AZ), and Turkish (TR)
  - Voice translation (client provides speech-to-text)
  - Save translations for later
  - View translation history

- **Account Settings**
  - User-specific settings (default source/target languages, dark mode toggle)

---

 Available Endpoints

### Gmail
- `GET /api/emails` → Inbox
- `GET /api/emails/starred` → Starred mails
- `GET /api/emails/{id}` → Mail details
- `POST /api/emails` → Compose new mail
- `POST /api/emails/{id}/star` → Toggle starred

### Translate
- `POST /api/translate` → Text translation
- `POST /api/translate/voice` → Voice translation
- `GET /api/translate/history` → Translation history
- `GET /api/translate/saved` → Saved translations
- `POST /api/translate/saved` → Save/unsave a translation
- `GET /api/translate/languages` → Supported languages

### Account
- `GET /api/account` → Get user settings
- `PATCH /api/account` → Update user settings

---

##  Installation

### Prerequisites
- Java 17+
- Gradle (wrapper included)

### Clone and Run
```bash
git clone https://github.com/Esgerkhanova/Gmail-Application.git
cd Gmail-Application
./gradlew bootRun
