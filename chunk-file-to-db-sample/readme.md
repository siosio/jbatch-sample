# ファイルから読み込んだデータをデータベースに書き込むChunkステップのサンプル
CSVファイルからデータを読み込み、その内容をデータベースに書き込むChunkステップのサンプル。

このジョブは、以下の3つのステップから成る。

1. データベースにテーブルを作成するBatchletステップ
2. CSVからデータを読み込み、特定属性を大文字に変換しデータベースに格納するChunkステップ
3. 2番めのステップで登録したデータを取得し、ログに出力するBatchletステップ

## 実行方法
rootプロジェクト配下で、以下のコマンドで実行できます。

```bash
./gradlew :chunk-file-to-db-sample:run
```

###実行結果
ログ（標準出力）に、以下のメッセージが出力出来たら実行成功です。

```
23:08:55.410 [jberet-1] INFO siosio.PrintDatabaseBatchlet - user = User(id=1, name=HOGE)
23:08:55.411 [jberet-1] INFO siosio.PrintDatabaseBatchlet - user = User(id=2, name=FUGA)
23:08:55.412 [jberet-1] INFO siosio.PrintDatabaseBatchlet - user = User(id=3, name=PIYO)
```
