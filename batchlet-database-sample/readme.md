#データベースにアクセスするBatchletのサンプル
データベースにアクセスしてデータを登録するBatchletジョブ。

このジョブは、以下の3つのステップから成る。

1. データベースにテーブルを作成するBatchletステップ
2. データベースにデータを登録するBatchletステップ
3. 2番めのステップで登録したデータを取得し、ログに出力するBatchletステップ

## 実行方法
rootプロジェクト配下で、以下のコマンドで実行できます。

```bash
./gradlew :dbaccess-batchlet-sample:run
```


###実行結果
ログ（標準出力）に、以下のメッセージが出力出来たら実行成功です。

```
07:52:19.854 [jberet-1] INFO siosio.PrintDatabaseBatchlet - id:[1], name:[name_1]
07:52:19.855 [jberet-1] INFO siosio.PrintDatabaseBatchlet - id:[2], name:[name_2]
07:52:19.855 [jberet-1] INFO siosio.PrintDatabaseBatchlet - id:[3], name:[name_3]
07:52:19.856 [jberet-1] INFO siosio.PrintDatabaseBatchlet - id:[4], name:[name_4]
07:52:19.856 [jberet-1] INFO siosio.PrintDatabaseBatchlet - id:[5], name:[name_5]
07:52:19.857 [jberet-1] INFO siosio.PrintDatabaseBatchlet - id:[6], name:[name_6]
07:52:19.857 [jberet-1] INFO siosio.PrintDatabaseBatchlet - id:[7], name:[name_7]
07:52:19.858 [jberet-1] INFO siosio.PrintDatabaseBatchlet - id:[8], name:[name_8]
07:52:19.858 [jberet-1] INFO siosio.PrintDatabaseBatchlet - id:[9], name:[name_9]
07:52:19.859 [jberet-1] INFO siosio.PrintDatabaseBatchlet - id:[10], name:[name_10]
```
