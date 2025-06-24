# Maven + JDK17 (Eclipse Temurin)
FROM maven:3.9.6-eclipse-temurin-17

ARG UID
ARG GID

# Mavenのキャッシュ保存先を devuser のホームディレクトリに変更（非root対応）
ENV MAVEN_CONFIG=/home/devuser/.m2

RUN apt-get update && \
    groupadd -g $GID devgroup && \
    useradd -m -s /bin/bash -u $UID -g $GID devuser && \
    mkdir -p /workspace && \
    chown -R devuser:devgroup /workspace

# ユーザをdevuserに切り替え
# これにより、コンテナ内での操作がdevuser権限
USER devuser
WORKDIR /workspace

# WARビルドを手動でやる前提ならこのまま
CMD ["bash"]

# 自動でビルドさせるなら下記に変更も可：
# CMD ["mvn", "clean", "package"]