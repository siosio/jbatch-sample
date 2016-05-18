# flow elementのサンプル
flowを使うと複数のステップを1処理として纏める事ができる。
flow全体の処理結果お元に、次のステップの指定なんかもできるようになる。

## flow elementの使い方
下のジョブ定義のように、step定義などをflow要素に配置する。

このジョブ定義では、``flow1``に``flow1-step1``と``flow1-step2``の2つのステップがある。
``flow1-step1``→``flow1-step2``の順にステップが実行され、最終的な終了ステータスが``success``の場合に、``myStep``が実行される。
``success``以外の場合には、``myStep2``が実行される。

```xml
  <flow id="flow1">
    <step id="flow1-step1">
      <batchlet ref="batchlet1">
        <properties>
          <property name="property" value="step1" />
        </properties>
      </batchlet>
      <next on="ok" to="flow1-step2" />
    </step>

    <step id="flow1-step2">
      <batchlet ref="batchlet1">
        <properties>
          <property name="property" value="step2" />
        </properties>
      </batchlet>

      <end on="ok" exit-status="success" />
    </step>

    <next on="success" to="myStep" />
    <next on="*" to="myStep2" />
  </flow>
```

## 実行方法
rootプロジェクト配下で、以下のコマンドで実行できます。

```bash
./gradlew :flow-sample:run
```

### 実行結果
ログ（標準出力）に、以下のメッセージが出力出来たら実行成功です。
```
10:15:37.103 [jberet-1] INFO siosio.Batchlet1 - property = [step1]
10:15:37.103 [jberet-1] INFO siosio.Batchlet1 - property = [step2]
10:15:37.104 [jberet-1] INFO siosio.Batchlet1 - property = [myStep]
```

