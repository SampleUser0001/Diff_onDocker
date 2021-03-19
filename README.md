# TemplateMaven
Mavenのテンプレート。

## 使用方法

### プロジェクト作成

1. .envを修正する。
   - PACAGE_NAME、PROJECT_NAMEを修正する。
2. Dockerfileを必要に応じて修正する。
   - 取得イメージの修正
3. init.shを実行する。

### clean compile exec:java

```
docker-compose -f docker-compose_clean_compile_exec.yml up
```

### コンテナ内にコマンドを流す

```mvn_command.sh```を修正してから下記実行。

```
docker-compose up
```

## 参考

- [Docker hub:maven](https://hub.docker.com/_/maven)
- [docker docs:Compose における環境変数](https://matsuand.github.io/docs.docker.jp.onthefly/compose/environment-variables/)