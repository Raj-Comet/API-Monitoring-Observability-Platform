<div align="center">

# 🔍 API Monitoring & Observability Platform

### A comprehensive full-stack solution for real-time API tracking, performance monitoring, and intelligent alerting

## 📋 Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [Architecture](#-architecture)
- [Technology Stack](#-technology-stack)
- [Quick Start](#-quick-start)
- [Installation](#-installation)
- [Configuration](#-configuration)
- [API Documentation](#-api-documentation)
- [Database Schema](#-database-schema)
- [Design Decisions](#-design-decisions)
- [Testing](#-testing)
- [Deployment](#-deployment)
- [Contributing](#-contributing)
- [License](#-license)
- [Support](#-support)

---

## 🎯 Overview

The **API Monitoring & Observability Platform** is a production-ready, distributed system designed to track, analyze, and monitor API requests across multiple microservices. It provides real-time insights into API performance, automatic issue detection, and intelligent alerting.

### Why This Platform?

- **🚀 Zero-Config Integration** - Add a single dependency to start monitoring
- **⚡ Real-Time Monitoring** - Track every API request with millisecond precision
- **🎯 Smart Alerting** - Automatic detection of slow APIs, errors, and rate limits
- **📊 Rich Analytics** - Beautiful dashboards with actionable insights
- **🔒 Secure** - JWT authentication with BCrypt password hashing
- **💪 Scalable** - Handles 50+ concurrent requests without breaking a sweat
- **🎨 Modern UI** - Sleek Next.js dashboard with real-time updates

---

## ✨ Features

### Core Capabilities

#### 🔌 Distributed API Tracking
```kotlin
// Just add the dependency - automatic monitoring starts!
implementation("com.monitoring:tracking-client:1.0.0")
```
- **Automatic request interception** for all Spring Boot endpoints
- **Comprehensive metrics** - latency, status codes, request/response sizes
- **Async logging** - non-blocking, doesn't slow down your APIs
- **Service identification** - track requests across multiple microservices

#### 🚦 Intelligent Rate Limiting
- **Token bucket algorithm** for smooth rate control
- **Configurable limits** per service (default: 100 req/s)
- **Non-blocking** - requests continue even when limit is exceeded
- **Automatic alerting** on rate limit violations

```yaml
monitoring:
  rateLimit:
    limit: 120  # Customize per service
```

#### 🗄️ Dual MongoDB Architecture
- **Logs Database** - High-volume request data (optimized for writes)
- **Metadata Database** - Users, alerts, issues (optimized for reads)
- **Independent scaling** and backup strategies
- **Transaction support** for critical operations

#### 🔐 Enterprise-Grade Security
- **JWT Authentication** with automatic token refresh
- **BCrypt password hashing** (10 rounds)
- **Role-based access control** (RBAC)
- **CORS protection** with configurable origins

#### 🎯 Smart Issue Detection

| Issue Type | Detection Criteria | Alert Severity |
|------------|-------------------|----------------|
| **Slow API** | Latency > 500ms | Medium to Critical |
| **Broken API** | Status 5xx | Critical |
| **Rate Limit** | Requests exceed limit | High |

#### 📊 Powerful Dashboard

<details>
<summary><b>Dashboard Features (Click to expand)</b></summary>

- **Real-time Statistics**
  - Total requests processed
  - Average API latency
  - Error rate percentage
  - Active alerts count

- **Interactive Charts**
  - Top 5 slowest endpoints (bar chart)
  - Error rate over time (line graph)
  - Request distribution by service
  - Latency trends

- **Advanced Filtering**
  - Filter by service name
  - Filter by endpoint path
  - Date range selection
  - Quick filters (slow APIs, broken APIs, rate limits)

- **Issue Management**
  - Track open issues
  - Mark issues as resolved
  - Optimistic locking prevents conflicts
  - Audit trail (who resolved, when)

</details>

---

## 🏗️ Architecture

### System Overview

```mermaid
graph TD
    A[Microservice 1] -->|Tracking Client| D[Collector Service]
    B[Microservice 2] -->|Tracking Client| D
    C[Microservice N] -->|Tracking Client| D
    D --> E[(MongoDB Logs)]
    D --> F[(MongoDB Metadata)]
    D --> G[Dashboard API]
    G --> H[Next.js Dashboard]
    
    style D fill:#0ea5e9,color:#fff
    style H fill:#000,color:#fff
    style E fill:#4caf50,color:#fff
    style F fill:#4caf50,color:#fff
```

### Component Architecture

<table>
<tr>
<td width="33%">

#### 📦 Tracking Client
- Spring Boot library
- HTTP interceptor
- Rate limiter
- Async log sender

</td>
<td width="33%">

#### 🏢 Collector Service
- REST API endpoints
- Alert generation
- Issue tracking
- JWT authentication

</td>
<td width="33%">

#### 📊 Dashboard
- Next.js frontend
- Real-time charts
- Log explorer
- Issue management

</td>
</tr>
</table>

### Request Flow

```
1. API Request → Tracking Interceptor
2. Rate Limiter Check → Allow/Log violation
3. Request Processing → Calculate latency
4. Async Send → Collector Service
5. Store Logs → MongoDB (Logs DB)
6. Analysis → Generate alerts if needed
7. Store Metadata → MongoDB (Metadata DB)
8. Dashboard → Query & Display
```

---

## 🛠️ Technology Stack

### Backend

| Technology | Version | Purpose |
|------------|---------|---------|
| ![Kotlin](https://img.shields.io/badge/-Kotlin-7F52FF?logo=kotlin&logoColor=white) | 1.9.21 | Primary language |
| ![Spring Boot](https://img.shields.io/badge/-Spring%20Boot-6DB33F?logo=spring-boot&logoColor=white) | 3.2.0 | Application framework |
| ![MongoDB](https://img.shields.io/badge/-MongoDB-47A248?logo=mongodb&logoColor=white) | 6.0+ | Dual databases |
| ![JWT](https://img.shields.io/badge/-JWT-000000?logo=json-web-tokens&logoColor=white) | 0.12.3 | Authentication |
| ![Gradle](https://img.shields.io/badge/-Gradle-02303A?logo=gradle&logoColor=white) | 8.5 | Build tool |

### Frontend

| Technology | Version | Purpose |
|------------|---------|---------|
| ![Next.js](https://img.shields.io/badge/-Next.js-000000?logo=next.js&logoColor=white) | 14.0 | React framework |
| ![TypeScript](https://img.shields.io/badge/-TypeScript-3178C6?logo=typescript&logoColor=white) | 5.2 | Type safety |
| ![Tailwind](https://img.shields.io/badge/-Tailwind%20CSS-06B6D4?logo=tailwind-css&logoColor=white) | 3.3 | Styling |
| ![Recharts](https://img.shields.io/badge/-Recharts-FF6B6B?logo=chart.js&logoColor=white) | 2.10 | Charts |
| ![Axios](https://img.shields.io/badge/-Axios-5A29E4?logo=axios&logoColor=white) | 1.6 | HTTP client |

---

## 🚀 Quick Start

### Prerequisites

```bash
# Check your environment
docker --version    # Docker 20.10+
java -version       # Java 17+
node --version      # Node 18+
npm --version       # npm 9+
```

### One-Command Setup

```bash
# Clone the repository
git clone https://github.com/yourusername/api-monitoring-platform.git
cd api-monitoring-platform

# Run automated setup
chmod +x setup.sh
./setup.sh
```

**That's it!** 🎉 The platform will be running at:
- 📊 Dashboard: http://localhost:3000
- 🔧 API: http://localhost:8080
- 🗄️ MongoDB Logs: mongodb://localhost:27017
- 🗄️ MongoDB Metadata: mongodb://localhost:27018

### Default Credentials

```
Username: admin
Password: admin123
```

### Generate Test Data

```bash
./test-api-monitoring.sh
```

This creates sample logs, alerts, and issues for you to explore.

---

## 📦 Installation

<details>
<summary><b>Manual Installation (Click to expand)</b></summary>

### Step 1: Start MongoDB Instances

```bash
# Logs Database
docker run -d --name mongodb-logs -p 27017:27017 \
  -e MONGO_INITDB_ROOT_USERNAME=admin \
  -e MONGO_INITDB_ROOT_PASSWORD=admin123 \
  mongo:latest

# Metadata Database
docker run -d --name mongodb-metadata -p 27018:27017 \
  -e MONGO_INITDB_ROOT_USERNAME=admin \
  -e MONGO_INITDB_ROOT_PASSWORD=admin123 \
  mongo:latest
```

### Step 2: Build Tracking Client

```bash
cd tracking-client
./gradlew build
cd ..
```

### Step 3: Start Collector Service

```bash
cd collector-service
./gradlew bootRun
```

Wait for: `Started CollectorApplication in X seconds`

### Step 4: Create Admin User

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username": "admin", "password": "admin123"}'
```

### Step 5: Start Dashboard

```bash
cd dashboard
npm install
npm run dev
```

Visit: http://localhost:3000

</details>

---

## ⚙️ Configuration

### Tracking Client Configuration

Add to your `application.yml`:

```yaml
monitoring:
  enabled: true
  collectorUrl: http://localhost:8080/api/logs
  serviceName: your-service-name
  rateLimit:
    enabled: true
    limit: 100  # requests per second
```

### Collector Service Configuration

```yaml
server:
  port: 8080

spring:
  data:
    mongodb:
      # Logs Database
      logs:
        uri: mongodb://admin:admin123@localhost:27017/logs_db?authSource=admin
        database: logs_db
      
      # Metadata Database
      metadata:
        uri: mongodb://admin:admin123@localhost:27018/metadata_db?authSource=admin
        database: metadata_db

logging:
  level:
    com.monitoring.collector: DEBUG
```

### Environment Variables

| Variable | Default | Description |
|----------|---------|-------------|
| `SERVER_PORT` | 8080 | Collector API port |
| `MONGO_LOGS_URI` | localhost:27017 | Logs database connection |
| `MONGO_METADATA_URI` | localhost:27018 | Metadata database connection |
| `JWT_SECRET` | (random) | JWT signing key |
| `JWT_EXPIRATION` | 86400000 | Token expiration (ms) |

---

## 📡 API Documentation

### Authentication Endpoints

<details>
<summary><code>POST /api/auth/register</code> - Register new user</summary>

**Request:**
```json
{
  "username": "john_doe",
  "password": "secure_password"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "username": "john_doe"
}
```

</details>

<details>
<summary><code>POST /api/auth/login</code> - Login user</summary>

**Request:**
```json
{
  "username": "admin",
  "password": "admin123"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "username": "admin"
}
```

</details>

### Log Collection Endpoints

<details>
<summary><code>POST /api/logs</code> - Send API log</summary>

**Request:**
```json
{
  "serviceName": "orders-service",
  "endpoint": "/api/orders",
  "method": "POST",
  "statusCode": 200,
  "latency": 450,
  "requestSize": 1024,
  "responseSize": 2048,
  "timestamp": "2024-12-06T10:00:00Z"
}
```

**Response:** `200 OK`

</details>

<details>
<summary><code>GET /api/logs</code> - Query logs (requires auth)</summary>

**Query Parameters:**
- `serviceName` - Filter by service
- `endpoint` - Filter by endpoint
- `startDate` - ISO 8601 timestamp
- `endDate` - ISO 8601 timestamp
- `slowOnly` - Boolean (latency > 500ms)
- `brokenOnly` - Boolean (status >= 500)

**Example:**
```bash
curl -H "Authorization: Bearer YOUR_TOKEN" \
  "http://localhost:8080/api/logs?serviceName=orders&slowOnly=true"
```

</details>

### Dashboard Endpoints

<details>
<summary><code>GET /api/dashboard/stats</code> - Get dashboard statistics</summary>

**Response:**
```json
{
  "slowApiCount": 15,
  "brokenApiCount": 3,
  "rateLimitViolations": 8,
  "totalRequests": 1000,
  "avgLatency": 250.5
}
```

</details>

<details>
<summary><code>GET /api/dashboard/top-endpoints</code> - Get slowest endpoints</summary>

**Query Parameters:**
- `limit` - Number of results (default: 5)

**Response:**
```json
[
  {
    "endpoint": "/api/orders",
    "avgLatency": 1250.5,
    "requestCount": 150
  }
]
```

</details>

<details>
<summary><code>POST /api/dashboard/issues/resolve</code> - Resolve an issue</summary>

**Request:**
```json
{
  "issueId": "507f1f77bcf86cd799439011"
}
```

**Response:** Updated issue object

</details>

---

## 🗄️ Database Schema

### Logs Database (MongoDB)

#### Collection: `api_logs`
```javascript
{
  _id: ObjectId,
  serviceName: String,      // "orders-service"
  endpoint: String,          // "/api/orders"
  method: String,            // "POST"
  statusCode: Number,        // 200
  latency: Number,           // 450 (milliseconds)
  requestSize: Number,       // 1024 (bytes)
  responseSize: Number,      // 2048 (bytes)
  timestamp: ISODate         // "2024-12-06T10:00:00Z"
}
```

**Indexes:**
- `serviceName_1_endpoint_1`
- `timestamp_-1`
- `latency_1`
- `statusCode_1`

#### Collection: `rate_limit_events`
```javascript
{
  _id: ObjectId,
  serviceName: String,
  endpoint: String,
  timestamp: ISODate,
  currentRate: Number,      // 150
  limit: Number             // 100
}
```

### Metadata Database (MongoDB)

#### Collection: `users`
```javascript
{
  _id: ObjectId,
  username: String,         // Unique
  password: String,         // BCrypt hashed
  role: String              // "USER" | "ADMIN"
}
```

#### Collection: `issues`
```javascript
{
  _id: ObjectId,
  serviceName: String,
  endpoint: String,
  issueType: String,        // "SLOW_API" | "BROKEN_API" | "RATE_LIMIT_HIT"
  description: String,
  firstDetected: ISODate,
  lastOccurrence: ISODate,
  occurrenceCount: Number,
  resolved: Boolean,
  resolvedBy: String,       // Username
  resolvedAt: ISODate,
  version: Number           // For optimistic locking
}
```

#### Collection: `alerts`
```javascript
{
  _id: ObjectId,
  serviceName: String,
  endpoint: String,
  alertType: String,        // "HIGH_LATENCY" | "SERVER_ERROR" | "RATE_LIMIT_EXCEEDED"
  message: String,
  severity: String,         // "LOW" | "MEDIUM" | "HIGH" | "CRITICAL"
  timestamp: ISODate,
  acknowledged: Boolean
}
```

---

## 🎯 Design Decisions

### 1. Why Dual MongoDB Setup?

**Decision:** Use two separate MongoDB instances instead of one.

**Rationale:**
- **Performance Isolation** - Logs are write-heavy, metadata is read-heavy
- **Independent Scaling** - Scale databases based on their specific needs
- **Backup Strategies** - Different RPO/RTO for logs vs metadata
- **Schema Evolution** - Changes to one don't affect the other

**Alternative Considered:** Single database with multiple collections
**Why Not:** Would create contention between high-volume writes and frequent reads

### 2. Optimistic Locking for Concurrency

**Decision:** Use `@Version` annotation with retry mechanism.

**Example:**
```kotlin
@Document
data class Issue(
    @Version val version: Long? = null,  // Automatic optimistic locking
    val resolved: Boolean = false
)

@Retryable(
    value = [OptimisticLockingFailureException::class],
    maxAttempts = 5,
    backoff = Backoff(delay = 100)
)
fun resolveIssue(issueId: String, resolvedBy: String): Issue
```

**Why This Approach:**
- ✅ **Non-blocking** - No locks held during user think time
- ✅ **Automatic retries** - Handles conflicts gracefully
- ✅ **Simple** - No distributed lock coordination needed
- ✅ **Low contention** - Issue resolution is relatively rare

**Alternative Considered:** Pessimistic locking with Redis
**Why Not:** Adds complexity and external dependency for low-contention scenario

### 3. Token Bucket Rate Limiting

**Decision:** Use token bucket algorithm via Bucket4j.

**Algorithm Visualization:**
```
Time ─────────────────────────────►
      [Tokens: 100]
      ↓ consume(1)
      [Tokens: 99]
      ↓ consume(1)
      [Tokens: 98]
      ...
      [Tokens: 0] ← Rate limit hit!
      ↓ refill (1/second)
      [Tokens: 1]
```

**Why Token Bucket:**
- ✅ Allows controlled bursts
- ✅ Smooth rate enforcement
- ✅ Industry standard (AWS, GCP use it)
- ✅ Memory efficient

**Alternative Considered:** Fixed window counter
**Why Not:** Vulnerable to burst at window boundaries

### 4. Asynchronous Log Sending

**Decision:** Send logs asynchronously using coroutines.

```kotlin
scope.launch {
    try {
        restTemplate.postForEntity(url, log, String::class.java)
    } catch (e: Exception) {
        logger.error("Failed to send log", e)
    }
}
```

**Benefits:**
- ✅ **Non-blocking** - Doesn't slow down API requests
- ✅ **Fault tolerant** - If collector is down, requests still work
- ✅ **Fire-and-forget** - No waiting for acknowledgment

**Tradeoff:** Potential for log loss if service crashes
**Mitigation:** Could add local queue with disk persistence (future enhancement)

---

## 🧪 Testing

### Unit Tests

```bash
# Backend
cd collector-service
./gradlew test

# Frontend
cd dashboard
npm test
```

### Integration Tests

```bash
# Start all services
./setup.sh

# Run integration tests
./test-api-monitoring.sh
```

### Manual Testing Scenarios

#### Test 1: Normal API Request
```bash
curl -X POST http://localhost:8080/api/logs \
  -H "Content-Type: application/json" \
  -d '{
    "serviceName": "test-service",
    "endpoint": "/api/test",
    "method": "GET",
    "statusCode": 200,
    "latency": 100,
    "requestSize": 512,
    "responseSize": 1024,
    "timestamp": "'$(date -u +%Y-%m-%dT%H:%M:%SZ)'"
  }'
```

✅ **Expected:** Log appears in dashboard

#### Test 2: Slow API Detection
```bash
curl -X POST http://localhost:8080/api/logs \
  -H "Content-Type: application/json" \
  -d '{
    "serviceName": "slow-service",
    "endpoint": "/api/slow",
    "method": "GET",
    "statusCode": 200,
    "latency": 1500,
    "requestSize": 512,
    "responseSize": 1024,
    "timestamp": "'$(date -u +%Y-%m-%dT%H:%M:%SZ)'"
  }'
```

✅ **Expected:** 
- Alert generated with HIGH severity
- Issue created in Issues panel
- "Slow APIs" counter increments

#### Test 3: Rate Limit Trigger
```bash
for i in {1..150}; do
  curl -X POST http://localhost:8080/api/rate-limit-events \
    -H "Content-Type: application/json" \
    -d '{
      "serviceName": "overloaded-service",
      "endpoint": "/api/orders",
      "timestamp": "'$(date -u +%Y-%m-%dT%H:%M:%SZ)'",
      "currentRate": 150,
      "limit": 100
    }' &
done
wait
```

✅ **Expected:**
- Multiple rate limit alerts
- "Rate Limit Violations" counter updates
- Issue created

#### Test 4: Concurrent Issue Resolution
1. Open two browser windows
2. Login as different users
3. Click "Mark Resolved" on same issue simultaneously
4. One succeeds immediately, other retries and succeeds

✅ **Expected:**
- No duplicate resolutions
- Both users see issue as resolved
- `version` field increments by 1 (not 2)

### Performance Benchmarks

```bash
# Load test with Apache Bench
ab -n 1000 -c 50 -p payload.json -T application/json \
   http://localhost:8080/api/logs
```

**Expected Results:**
- 🎯 50 concurrent requests handled successfully
- 🎯 99% of requests complete in < 100ms
- 🎯 No request failures
- 🎯 Rate limiter triggers correctly

---

## 🤝 Contributing

We love contributions! Here's how you can help:

### Development Setup

1. **Fork the repository**
   ```bash
   git clone https://github.com/yourusername/api-monitoring-platform.git
   cd api-monitoring-platform
   ```

2. **Create a feature branch**
   ```bash
   git checkout -b feature/amazing-feature
   ```

3. **Make your changes**
   ```bash
   # Make changes
   git add .
   git commit -m "Add amazing feature"
   ```

4. **Push and create PR**
   ```bash
   git push origin feature/amazing-feature
   ```

### Contribution Guidelines

- ✅ Write tests for new features
- ✅ Follow Kotlin coding conventions
- ✅ Update documentation
- ✅ Add comments for complex logic
- ✅ Ensure all tests pass

### Code of Conduct

Please read our [Code of Conduct](CODE_OF_CONDUCT.md) before contributing.

---

## 📄 License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

```
MIT License

Copyright (c) 2024 API Monitoring Platform

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction...
```

---

## 🆘 Support

### Documentation

- 📖 **[Full Documentation](docs/)** - Comprehensive guides
- 🎓 **[Tutorials](docs/tutorials/)** - Step-by-step tutorials
- 📚 **[API Reference](docs/api/)** - Complete API documentation
- ❓ **[FAQ](docs/faq.md)** - Frequently asked questions


### Issues & Bugs

Found a bug? Have a feature request?

1. **Search existing issues** - Maybe it's already reported
2. **Create a new issue** - Use our templates
3. **Provide details** - Logs, screenshots, reproduction steps

[Report a Bug](https://github.com/yourusername/api-monitoring-platform/issues/new?template=bug_report.md) | [Request a Feature](https://github.com/yourusername/api-monitoring-platform/issues/new?template=feature_request.md)

---

## 🎖️ Acknowledgments

### Built With

- [Spring Boot](https://spring.io/projects/spring-boot) - Application framework
- [Kotlin](https://kotlinlang.org/) - Programming language
- [Next.js](https://nextjs.org/) - React framework
- [MongoDB](https://www.mongodb.com/) - Database
- [Tailwind CSS](https://tailwindcss.com/) - CSS framework
- [Recharts](https://recharts.org/) - Charting library
- [Bucket4j](https://github.com/bucket4j/bucket4j) - Rate limiting

### Contributors

Thanks to all contributors who helped build this project! 🙏

<a href="https://github.com/yourusername/api-monitoring-platform/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=yourusername/api-monitoring-platform" />
</a>

---

## 📊 Project Stats

![GitHub stars](https://img.shields.io/github/stars/yourusername/api-monitoring-platform?style=social)
![GitHub forks](https://img.shields.io/github/forks/yourusername/api-monitoring-platform?style=social)
![GitHub watchers](https://img.shields.io/github/watchers/yourusername/api-monitoring-platform?style=social)

![GitHub issues](https://img.shields.io/github/issues/yourusername/api-monitoring-platform)
![GitHub pull requests](https://img.shields.io/github/issues-pr/yourusername/api-monitoring-platform)
![GitHub last commit](https://img.shields.io/github/last-commit/yourusername/api-monitoring-platform)

---

## 🗺️ Roadmap

- [x] Core monitoring functionality
- [x] JWT authentication
- [x] Dual MongoDB setup
- [x] Real-time dashboard
- [ ] **Q1 2025** - WebSocket support for real-time updates
- [ ] **Q2 2025** - Machine learning-based anomaly detection
- [ ] **Q2 2025** - Grafana/Prometheus integration
- [ ] **Q3 2025** - Distributed tracing (OpenTelemetry)
- [ ] **Q4 2025** - Multi-tenancy support
- [ ] **2026** - Cloud-native deployment (AWS, GCP, Azure)

---

<div align="center">

## ⭐ Star History

[![Star History Chart](https://api.star-history.com/svg?repos=yourusername/api-monitoring-platform&type=Date)](https://star-history.com/#yourusername/api-monitoring-platform&Date)

---

### Made with ❤️ by Raj, for LEAP FINANCE

**[⬆ Back to Top](#-api-monitoring--observability-platform)**

</div>
