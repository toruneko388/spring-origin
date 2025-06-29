# spring-origin

Docker Compose で **Spring Framework (非 Spring Boot)** + Tomcat の開発から実行までを一貫して行える最小構成プロジェクトです。VS Code の **Dev Containers** やリモートデバッグにも対応しているため、ホスト環境を汚さずに Java × Spring MVC の学習や PoC が行えます。

---

## 特長

- **Spring Framework 5.3.38** (2025‑05 時点の最新 LTS) を採用
- **非 Spring Boot** ─ 純粋な Spring MVC + WAR 形式で構成
- **完全コンテナ化** ─ `dev` / `builder` / `appserver` / `webserver` の 4 サービスに役割を分離
- **VS Code Dev Containers 対応** ─ `.devcontainer/` を同梱
- **Makefile で簡単操作** ─ `make build` / `make deploy` などをワンライナーで実行
- **リモートデバッグ** ─ JPDA 8000 ポートを公開し IDE からアタッチ可能
- **非 root ユーザー対応** ─ `UID` / `GID` を渡してホストと権限を揃えられる

---

## 前提環境

| ツール | バージョン例 |
| ------ | ------------ |
| Docker | 24.x 以上 |
| Docker Compose | v2 系 |
| (任意) VS Code | 最新安定版 + *Dev Containers* 拡張 |

---

## クイックスタート

```bash
# 1. リポジトリを取得
$ git clone https://github.com/toruneko388/spring-origin.git
$ cd spring-origin

# 2. 一撃でビルド → デプロイ
$ make reload

#   └─ 内部では
#      make build   # Maven ビルド (builder サービス)
#      make deploy  # Tomcat + Apache を起動 (appserver / webserver)
```

アクセス確認:

- Apache 経由: <http://localhost/>
- Tomcat 直結: <http://localhost:8080/spring-webapp/hello>

---

## プロジェクト構成

```
.
├── .devcontainer/          # VS Code Remote Container 定義
├── development/            # 各サービス用 Dockerfile 群
│   ├── Dockerfile.dev
│   ├── Dockerfile.builder
│   ├── Dockerfile.appserver
│   └── Dockerfile.webserver
├── spring-webapp/          # Maven-based Spring MVC アプリ (WAR)
│   └── src/main/...
├── docker-compose.yml      # サービス定義
├── Makefile                # よく使う compose コマンドをラップ
└── README.md
```

---

## サービス一覧

| サービス | 役割 | 主なポート |
| -------- | ---- | ---------- |
| **dev** | VS Code などで作業するシェル用コンテナ | - |
| **builder** | Maven ビルド (JDK 17) | - |
| **appserver** | Tomcat 9 (WAR デプロイ先) | 8080 (HTTP), 8000 (JPDA) |
| **webserver** | Apache httpd (リバースプロキシ) | 80 |

---

## Makefile コマンド早見表

```bash
make build     # WAR をビルド (mvn clean package)
make deploy    # appserver / webserver を起動
make reload    # build + deploy をワンライナー実行
make stop      # すべてのコンテナ停止
make logs      # コンテナログをフォロー
```

---

## リモートデバッグ手順

1. `make reload` でコンテナを起動（ポート 8000 が開放される）。
2. IDE の "Attach Debugger" で下記設定を入力して接続:
   - **Host**: `localhost`
   - **Port**: `8000`
   - **Transport**: `dt_socket`

---

## UID/GID カスタマイズと非 root 対応

ホストとコンテナ間のファイル権限ズレを防ぐため、`docker compose build` 実行前に以下の環境変数を指定できます。

```bash
export UID=$(id -u)
export GID=$(id -g)
```

これにより、`builder` イメージ内で非 root ユーザー `devuser` が `UID/GID` で作成され、Maven キャッシュ (`~/.m2`) も同ユーザー所有となります。

---

## 今後のロードマップ

- SSL導入
- networks を定義して外部公開サービスだけ default に載せる
- ログイン機能
- リダイレクト
- X-Forwarded-For など追加・変更
- 鍵の追加
- ORマッパー、AOP、バリデーション
- CI/CD (GitHub Actions) での自動ビルド
- API
