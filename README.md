
# Kafka Batch Producer & Consumer Service

## Overview

Project ini merupakan contoh implementasi Apache Kafka menggunakan Spring Boot dengan fitur:

* Upload file (CSV) melalui REST endpoint
* Streaming file dan mengirim data per baris ke Kafka
* Producer dengan batching & compression
* Consumer dengan batch processing

Project ini dibuat untuk eksplorasi dan pembelajaran arsitektur event-driven serta optimasi message processing dalam skala besar (ratusan ribu record).

---

## Tech Stack

* Java 17+
* Spring Boot
* Apache Kafka
* Gradle
* Docker (optional untuk Kafka broker)

---

## Running the Project

### 1. Jalankan Kafka

Pastikan Kafka sudah berjalan.

Contoh via Docker:

```
docker compose up -d
```

Atau gunakan cluster Kafka yang sudah ada.

---

### 2. Jalankan Service

```
./gradlew bootRun
```

---

### 3. Upload File

Endpoint:

```
POST /send
```

Gunakan `multipart/form-data` dengan key:

```
file
```

File akan diproses dan dikirim ke Kafka.

---

## Purpose

Project ini dibuat untuk memahami:

* Perbedaan single message vs batch processing
* Cara kerja offset dan consumer group
* Dampak compression & batching
* Optimasi upload file besar ke Kafka

