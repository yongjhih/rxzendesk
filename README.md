# RxZendesk

[![CircleCI](https://circleci.com/gh/yongjhih/rxzendesk.svg?style=shield)](https://circleci.com/gh/yongjhih/rxzendesk)
[![codecov](https://codecov.io/gh/yongjhih/rxzendesk/branch/master/graph/badge.svg)](https://codecov.io/gh/yongjhih/rxzendesk)

## Usage

```java
ZendeskConfig.INSTANCE
             .provider()
             .helpCenterProvider()
             .getArticles(sectionId, new ZendeskCallback<List<Article>>() {
                  @Override public void onSuccess(List<Article> articles) {
                      ViewArticleActivity.startActivity(MainActivity.this, articles.get(0));
                  }
                  // ...
             });
```

After:

```java
RxZendesk(ZendeskConfig.INSTANCE.provider().helpCenterProvider())
         .getArticles(sectionId)
         .subscribe(articles -> {
              ViewArticleActivity.startActivity(MainActivity.this, articles.get(0));
         });
```

After:

```kt
ZendeskConfig.INSTANCE.provider().helpCenterProvider()
         .getsArticles(sectionId)
         .subscribe { articles ->
              ViewArticleActivity.startActivity(MainActivity.this, articles.get(0))
         })
```

## Installation

RxJava2

```gradle
repositories {
    jcenter()
    maven { url 'https://zendesk.jfrog.io/zendesk/repo' }
    maven { url "https://jitpack.io" }
}

dependencies {
    compile 'com.github.yongjhih.rxzendesk:rxzendesk:0.0.1'
    //compile 'com.github.yongjhih.rxzendesk:rxzendesk-kotlin:0.0.1'
}
```

## Ref.

* https://zdmobilesdkdocdev.herokuapp.com/android-sdk/
* https://zdmobilesdkdocdev.herokuapp.com/android-sdk-providers
* https://developer.zendesk.com/embeddables/docs/android

## LICENSE

```
Copyright 2017 Andrew Chen

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
