# Makefile

# Mavenビルド用ターゲット
build:
	docker compose run --rm dev bash -c "cd spring-webapp && mvn clean package"

# WARをTomcatに反映
deploy:
	docker compose up --build -d web

# 一括でビルド＆デプロイ
reload: build deploy
