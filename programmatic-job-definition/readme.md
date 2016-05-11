# ロジックでジョブ定義を組み立てるサンプル

## ロジックでジョブ定義を組み立てる方法
``JobBuilder`` や ``StepBuilder`` を使うことでジョブ定義を構築できる。

詳細は、JBeretのドキュメントを参照
* https://jberet.gitbooks.io/jberet-user-guide/content/programmatic_job_definition_with_java/index.html

## ロジックで組み立てたジョブの実行方法
JSRでは規定されていないので、JBeretの実装を直接使う必要があります。

具体的には、以下の実装のように ``BatchRuntime`` から取得した ``JobOperrator`` を
JBeretの実装である ``JobOperatorImpl`` にダウンキャストする必要があります。

実行するには、 ``JobOperatorImpl#start`` に ``JobBuilder`` で構築した ``Job`` オブジェクトを指定してあげます。

```kotlin
val jobOperator = BatchRuntime.getJobOperator() as JobOperatorImpl
val executionId = jobOperator.start(job, null)
```


## 実行方法
rootプロジェクト配下で、以下のコマンドで実行できます。

```bash
./gradlew :programmatic-job-definition:run_batchlet
```

### 実行結果
ログ（標準出力）に、以下のメッセージが出力出来たら実行成功です。
```
16:24:50.274 [jberet-1] INFO siosio.SampleBatchlet - 呼びだされた
16:24:50.279 [jberet-1] INFO siosio.SampleItemWriter - 0 = [1]
16:24:50.279 [jberet-1] INFO siosio.SampleItemWriter - 1 = [2]
16:24:50.280 [jberet-1] INFO siosio.SampleItemWriter - 0 = [3]
16:24:50.280 [jberet-1] INFO siosio.SampleItemWriter - 1 = [4]
16:24:50.280 [jberet-1] INFO siosio.SampleItemWriter - 0 = [5]
```

