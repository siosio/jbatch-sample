# JPAを使うBatchletのサンプル
JPA(Hibernate)を使って、データベースアクセスを行うBatchletのサンプル

このジョブは、以下の3つのステップから成る。

1. データベースにテーブルを作成するBatchletステップ
2. データベースにデータを登録するBatchletステップ
3. 2番めのステップで登録したデータを取得し、ログに出力するBatchletステップ

※このサンプルは、SE環境で実行することを想定しているので、 ``EntityManager`` をファクトリから生成しています。

## 実行方法
rootプロジェクト配下で、以下のコマンドで実行できます。

```bash
./gradlew :batchlet-jpa-sample:run
```

###実行結果
ログ（標準出力）に、以下のメッセージが出力出来たら実行成功です。

```
23:14:12.544 [jberet-1] DEBUG org.hibernate.internal.util.EntityPrinter - siosio.User{name=name_3, id=3}
23:14:12.544 [jberet-1] DEBUG org.hibernate.internal.util.EntityPrinter - siosio.User{name=name_4, id=4}
23:14:12.544 [jberet-1] DEBUG org.hibernate.internal.util.EntityPrinter - siosio.User{name=name_5, id=5}
23:14:12.544 [jberet-1] DEBUG org.hibernate.internal.util.EntityPrinter - siosio.User{name=name_1, id=1}
23:14:12.544 [jberet-1] DEBUG org.hibernate.internal.util.EntityPrinter - siosio.User{name=name_2, id=2}
```
