# Mavenビルド用ターゲット（builderコンテナを使用） -qオプションで出力を抑制
build:
	docker compose run --rm builder bash -c "cd spring-webapp && mvn -q clean package"

# WARをTomcatに反映（appserverとwebserverを起動）
deploy:
	docker compose up --build -d db appserver webserver

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

# PostgreSQLのコンテナに接続
dbpsql:
	docker compose exec db bash -c 'PGPASSWORD=$$POSTGRES_PASSWORD exec psql -U $$POSTGRES_USER -d $$POSTGRES_DB'

# Mavenのテスト実行（builderコンテナを使用）
test:
	docker compose run --rm builder bash -c "cd spring-webapp && mvn verify -Pwith-tests"