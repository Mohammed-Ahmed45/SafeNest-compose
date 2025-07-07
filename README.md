# 🛡️ SafeNest - Smart Safety Alert System

**SafeNest** is an IoT-integrated Android application designed to detect and alert users in real-time about environmental hazards \
such as fire, gas leaks, and water flooding. The system aims to reduce response time to incidents and enhance household safety through instant mobile notifications and emergency features.

---

## 🚀 Tech Stack

| Layer         | Technology                             |
|---------------|-----------------------------------------|
| Language      | Kotlin                                  |
| UI Toolkit    | Jetpack Compose                         |
| Architecture  | Clean Architecture (MVVM + UseCases)    |
| Dependency Injection | Dagger Hilt                    |
| Networking    | Retrofit + RESTful API                  |
| Realtime Alerts | Firebase Cloud Messaging (FCM)        |
| Async Handling | Kotlin Coroutines                     |

---

## 📱 Application Screenshots

<div align="center">
  <img src="screenshots/Screenshot%202025-07-07%20100454.png" width="250"/>
  <img src="screenshots/Screenshot%202025-07-07%20100609.png" width="250"/>
  <img src="screenshots/Screenshot%202025-07-07%20100631.png" width="250"/>
  <img src="screenshots/Screenshot%202025-07-07%20100711.png" width="250"/>
  <img src="screenshots/Screenshot%202025-07-07%20100729.png" width="250"/>
  <img src="screenshots/Screenshot%202025-07-07%20100805.png" width="250"/>
  <img src="screenshots/Screenshot%202025-07-07%20100816.png" width="250"/>
  <img src="screenshots/Screenshot%202025-07-07%20100835.png" width="250"/>
  <img src="screenshots/Screenshot%202025-07-07%20100851.png" width="250"/>
</div>

---

## ✨ Core Features

- 🔥 **Fire Detection** – Real-time fire alerts from IoT flame sensors.
- 💧 **Water Leak Detection** – Monitor flooding or leaks via connected sensors.
- 🛢️ **Gas Detection** – Notify users of dangerous gas presence instantly.
- 🛎️ **Push Notifications** – Seamless FCM integration for alert delivery.
- 📞 **Emergency Call Button** – Immediate dial-out to emergency services.
- 🧭 **Offline-Friendly UI** – Compose-based, responsive, and lightweight UI.
- 📡 **Wi-Fi Module Integration** – Receives sensor data via ESP module.

---

## 🧠 Project Architecture

The app is structured following **Clean Architecture Principles**, promoting separation of concerns and testability:

app/
├── data/          # Network & local sources
├── domain/        # Business logic and use cases
├── presentation/  # UI with Jetpack Compose
└── di/            # Dependency Injection (Hilt)



