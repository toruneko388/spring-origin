# Mavenビルド用ターゲット（builderコンテナを使用）
build:
	docker compose run --rm builder bash -c "cd spring-webapp && mvn clean package"

# WARをTomcatに反映（appserverとwebserverを起動）
deploy:
	docker compose up --build -d appserver webserver

# 一括でビルド＆デプロイ
reload: build deploy

# コンテナの停止
stop:
	docker compose down

# コンテナのログ表示（リアルタイム）
logs:
	docker compose logs -f

# Apache のみ再起動（設定変更時など）
restart-apache:
	docker compose restart webserver

# Tomcat のみ再起動（WARの差し替え後など）
restart-tomcat:
	docker compose restart appserver

builder-build-nocache:
	docker compose build builder --no-cache

appserver-build-nocache:
	docker compose build appserver --no-cache