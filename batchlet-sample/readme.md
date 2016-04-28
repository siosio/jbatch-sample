#Batchletのサンプル
ログに ``hello world!!!`` を出力するBatchletジョブです。

## Batchletの作成方法
Batchletは、 ``javax.batch.api.AbstractBatchlet.AbstractBatchlet`` を継承して作成する。
Batchletの処理は、 ``process`` メソッドに実装する。

## 実行方法
rootプロジェクト配下で、以下のコマンドで実行できます。

```bash
./gradlew :batchlet-sample:run
```

###実行結果
ログ（標準出力）に、以下のメッセージが出力出来たら実行成功です。

```
23:06:19.295 [jberet-1] INFO siosio.SampleBatchlet - hello world!!!
```
