# 🛡️ SafeNest

**SafeNest** is a smart safety alert system built using **Jetpack Compose** and IoT integration. It provides real-time notifications 
for fire, water, and gas threats, helping users stay protected and take action instantly.

---

## 🚀 Tech Stack

- **Kotlin + Jetpack Compose**
- **Firebase Cloud Messaging (FCM)**
- **Retrofit + REST API**
- **MVVM & Clean Architecture**
- **Dagger Hilt**
- **Coroutines**

---

## 📱 App Screenshots

<div align="center">
  <img src="screenshots/Screenshot%202025-07-07%20100729.png" width="250"/>
  <img src="screenshots/Screenshot%202025-07-07%20100805.png" width="250"/>
  <img src="screenshots/Screenshot%202025-07-07%20100816.png" width="250"/>
  <img src="screenshots/Screenshot%202025-07-07%20100835.png" width="250"/>
  <img src="screenshots/Screenshot%202025-07-07%20100851.png" width="250"/>
</div>

---

## 🔔 Features

- 🔥 Real-time **Fire Alerts**
- 💧 Instant **Water Leak Alerts**
- 🛢️ **Gas Detection** Notifications
- 📲 Push Notifications via Firebase
- 📞 Emergency Call Button
- 🗺️ Smart UI using Jetpack Compose
- ✅ Works Offline

---

## 🧠 Architecture

The app follows **Clean Architecture** with clear layer separation:

app/
├── data/          # Network & local sources
├── domain/        # Business logic and use cases
├── presentation/  # UI with Jetpack Compose
└── di/            # Dependency Injection (Hilt)


