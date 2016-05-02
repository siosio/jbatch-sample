# 停止処理を持つBatchletのサンプル
処理実行中に停止要求があった場合に、実行中の処理をキャンセルしてBatchletを終了するサンプル

# Batchletの作成方法
Batchletの ``stop`` メソッドに ``process`` メソッドで実行中の処理を中断する処理を実装します。
例えば、データベースへのSQL実行をキャンセルする処理などを実装します。

※ジョブのキャンセルを読んだ場合でも、 ``Batchlet`` の ``stop`` メソッドが何も実装していないと、
キャンセル処理が実行されないため ``Batchlet`` の終了を永遠待ち続けてしまうので注意が必要です。

キャンセル可能な ``Batchlet`` を作る場合には、必ず ``stop`` メソッド内で安全にステップを停止する処理を実装する必要があります。

## 実行方法
rootプロジェクト配下で、以下のコマンドで実行できます。

```bash
./gradlew :stop-batchlet-sample:run
```

###実行結果
ログ（標準出力）に、以下のメッセージが出力出来たら実行成功です。

このログから ``myStep`` ステップの実行中にキャンセル処理が実行され、
その後にジョブが再実行され ``myStep`` ステップが正常に終了していることがわかります。

なお、ジョブを再実行すると、ジョブの実行を一意に識別する ``executionId`` も新たに新しい物がふられます。
```
18:42:28.495 [main] INFO org.jboss.weld.Bootstrap - WELD-ENV-002003: Weld SE container STATIC_INSTANCE initialized
18:42:28.636 [main] INFO siosio.StopBatchAction - ジョブを開始しました。ジョブ名=[stop-batchlet], 実行ID=[1]
18:42:28.670 [jberet-1] INFO siosio.StopBatchlet - ステップの処理が開始されました。ステップ=[myStep]
18:42:33.654 [main] INFO siosio.StopBatchlet - ステップの実行をキャンセルします。ステップ=[myStep]
18:42:38.656 [main] INFO siosio.StopBatchAction - ジョブを再実行します。ジョブ名=[stop-batchlet], 実行ID=[1], ジョブステータス=[STOPPED]
18:42:38.663 [jberet-1] INFO siosio.StopBatchlet - ステップの処理が開始されました。ステップ=[myStep]
18:42:43.661 [main] INFO siosio.StopBatchAction - ジョブの再実行が終了しました。ジョブ名=[stop-batchlet], 実行ID=[2]
```

