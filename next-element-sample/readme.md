# next elementのサンプル
next elementを使うと、ステップの終了ステータスを元に次に実行すべきステップを切り替えることができるようになる。

## next elementの使い方
下のジョブ定義のように、step要素にnext要素を配置する。
on属性に設定した期待する終了ステータスと、実際の終了ステータスがマッチする場合、
to属性で指定したステップが実行される。

下の設定の場合、step1の終了ステータスが ``ok`` の場合にstep2が実行され、
``ok`` 以外の場合にstep3が実行される。

```xml
<step id="step1">
  <batchlet ref="step1Batchlet" />
  <next on="ok" to="step2" />
  <next on="*" to="step3" />
</step>

<step id="step2">
  <batchlet ref="step2Batchlet" />
</step>

<step id="step3">
  <batchlet ref="step3Batchlet" />
</step>
```

## batchletやchunkから終了ステータスを返す方法

### batchletの場合
``process`` メソッドの戻り値で終了ステータスを設定する。

下の``process``の場合、正常に処理が終わった場合の終了ステータスは、``ok``となる。

```kotlin
override fun process(): String? {
  logger.info("step1")
  return "ok"
}
```

### chunkの場合
ItemReaderやItemWriter、ItemProcessorにインジェクトしたStepContextに終了ステータスを設定する。
下の例の場合、終了ステータスは``ok``となる。
```kotlin
@Inject
lateinit private var stepContext: StepContext

override fun readItem(): Any? {
  logger.info("reader")
  stepContext.exitStatus = "ok"
  return null
}
```

## 実行方法
rootプロジェクト配下で、以下のコマンドで実行できます。

### batchletの場合
```bash
./gradlew :dbaccess-batchlet-sample:run_batchlet
```

#### 実行結果
ログ（標準出力）に、以下のメッセージが出力出来たら実行成功です。
```
22:14:40.864 [jberet-1] INFO siosio.Step1Batchlet - step1
22:14:40.868 [jberet-1] INFO siosio.Step2Batchlet - step2
```

### chunkの場合
```bash
./gradlew :dbaccess-batchlet-sample:run_chunk
```

#### 実行結果
ログ（標準出力）に、以下のメッセージが出力出来たら実行成功です。
```
22:15:57.968 [jberet-1] INFO siosio.Reader - reader
22:15:57.972 [jberet-1] INFO siosio.Step2Batchlet - step2
```
