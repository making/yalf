YALF is the abbreviation of "Yet Another Logger Facade".

## 特徴
**TODO: write in English**

YALFは各種ロギングライブラリの薄皮ラッパです。YALF経由でログ出力を行うと以下の利点があります。

 - ログメッセージをプロパティファイルで管理できる
 - ログ出力メソッドをログIDと置換パラメータで呼び出せる
 - 置換パラメータは可変長配列で渡せる
 - 出力されるログにログIDが自動で埋め込まれる
 - ログレベル確認のif文が(多くの場合)不要
 - ロガー実装はアダプタを選択することで容易に変更可能
 - モジュール毎にメッセージプロパティファイルを管理できる
 - メッセージプロパティファイルなしでも利用できる

ライブラリ開発時にYALFのログ出力APIを使い、そのライブラリを使用するアプリ側でアダプタを選ぶという使い方ができます。

## Examples
### ロガー取得
    import am.ik.yalf.logger.Logger;

でロガーをインポートします。ロガーの取得は他のライブラリとほぼ同じです。

    Logger logger = Logger.getLogger(XXX.class);

または

    Logger logger = Logger.getLogger("Hoge");

### ログ出力

次のようなログメッセージプロパティファイルがある場合

    DEB001=debug message
    ERR001=error message

このメッセージを出力するには

    logger.debug("DEB001"); 
    logger.error("ERR001");

のようにログレベルに応じたメソッドにログIDを渡して実行します。
出力メッセージは

    [DEB001] debug message
    [ERR001] error message

となります。**メッセージの前に[ログID]が自動で付きます**。

ログレベルは

 - FATAL
 - ERROR
 - WARN
 - INFO
 - DEBUG
 - TRACE

があります。`log(String logId)`メソッドを使用すると、ログIDの一文字目を見てログレベルを判断します。

    logger.log("ERR001"); // ログレベルはERROR

### パラメータ置換

出力するログメッセージを作成する際に、`java.text.MessageFormat`を使用しています。置換パラメータを可変長配列で渡すことができます。
   
    DEB002={0} is {1}.

という定義がある場合、

    logger.debug("DEB002", "hoge", "foo");

を実行すると出力メッセージは

    [DEB002] hoge is foo.

となります。

内部でメッセージ文字列を作成する際にログレベルのチェックを行っているので、

    if (logger.isDebugEnabled()) {
        logger.debug("DEB002", "hoge", "foo");
    }

というif文を書く必要がありません。(ただし、パラメータを作成する際にコストのかかるメソッドを呼び出している場合はif文を書いて明示的にログレベルチェックを行ってください。)

### メッセージプロパティファイルにないメッセージの出力

メッセージプロパティファイル中のログIDを渡す他に、直接メッセージを渡す方法があります。第1引数に`false`  を設定し、第2引数にメッセージ本文を直接記述できます。第3引数以降は置換パラメータです。この場合は当然ログIDは出力されません。

    logger.warn(false, "warn!!");
    logger.info(false, "Hello {0}!", "World");

出力メッセージは

    warn!!
    Hello World!

となります。

## 設定ファイル

クラスパス直下の`META-INF`ディレクトリに`yalf.properties`を作成してください。

### メッセージプロパティファイルのベースネーム設定
`yalf.properties`の`message.basename`キーにメッセージプロパティファイルのベースネームをクラスパス相対(FQCN)で設定してください。`java.util.ResourceBundle`で読み込むので、国際化に対応しています。

    message.basename=hoge

と書くとクラスパス直下の`hoge.properties`が読み込まれます。

    message.basename=hoge,foo,bar

のように半角カンマ区切りで設定すると全てを読み込みます。

**META-INF/yalf.properiesのmessage.basenameはモジュール毎に設定できます**。ロガーは全てのモジュール(jar)が持つ、`message.basename`の値をマージしてメッセージを取得します。

これにより、モジュール毎にログメッセージを管理することができます。

### 出力ログIDフォーマット設定

ログ出力時に自動で付加されるログIDのフォーマットを設定できます。`message.id.format`キーに`java.lang.String.format()`のフォーマット形式で設定してください。ログIDが文字列として渡されます。

設定しない場合は「`[%s]`」がデフォルト値として使用されます。

    message.id.format=[%-8s]

のように設定すると、モジュール間で異なる長さのログIDを左寄せで揃えて出力できます。

この設定値はモジュール毎に管理することはできません。クラスローダの読み込み優先度が一番高い`yalf.properties`の値が反映されます。
(通常、アプリ側の設定となります。)

## 対応ロギングライブラリ

以下のロギングライブラリはアダプタを作成済みです。YALF本体と一緒にアダプタもクラスパスに追加してください。
アダプタが見つからない場合は、自動的にjava.util.loggingが使用されます。

 - java.util.logging (yalf-jul)
  - ※ ERRORとFATALの区別がありません
 - Commons Logging (yalf-jcl)
 - SLF4J (yalf-slf4j)
  - ※ ERRORとFATALの区別がありません
 - Log4J (yalf-log4j)


## Use with Maven 

At first, add repository in your pom.

    <repositories>
        ...

        <repository>
            <id>making-dropbox-releases</id>
            <name>making's Maven Release Repository</name>
            <url>http://dl.dropbox.com/u/342817/maven/releases</url>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </repository>
    </repositories>

And add dependency.

    <dependencies>
        ...

        <!-- YALF Core -->
        <dependency>
            <groupId>am.ik.yalf</groupId>
            <artifactId>yalf</artifactId>
            <version>0.9.1</version>
        </dependency>

        <!-- Choose one adapter from the below -->

        <!-- YALF Adapter for java.util.logging -->
        <dependency>
            <groupId>am.ik.yalf</groupId>
            <artifactId>yalf-jul</artifactId>
            <version>0.9.1</version>
        </dependency>

        <!-- YALF Adapter for Commons Logging -->
        <dependency>
            <groupId>am.ik.yalf</groupId>
            <artifactId>yalf-jcl</artifactId>
            <version>0.9.1</version>
        </dependency>

        <!-- YALF Adapter for SLF4J -->
        <dependency>
            <groupId>am.ik.yalf</groupId>
            <artifactId>yalf-slf4j</artifactId>
            <version>0.9.1</version>
        </dependency>

        <!-- YALF Adapter for Log4j -->
        <dependency>
            <groupId>am.ik.yalf</groupId>
            <artifactId>yalf-log4j</artifactId>
            <version>0.9.1</version>
        </dependency>
    </dependencies>

## Required

 - JDK1.5+

## License
Licensed under the Apache License, Version 2.0.
