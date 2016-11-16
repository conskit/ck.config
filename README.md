# ck.config [![Build Status](https://travis-ci.org/conskit/ck.config.svg?branch=master)](https://travis-ci.org/conskit/ck.config) [![Dependencies Status](https://jarkeeper.com/conskit/ck.config/status.svg)](https://jarkeeper.com/conskit/ck.config) [![Clojars Project](https://img.shields.io/clojars/v/ck.config.svg)](https://clojars.org/ck.config)

Configuration module for [conskit](https://github.com/conskit/conskit)

## Installation
Add the dependency in the clojars badge above in your `project.clj`.

## Usage

Add the following to your `bootstrap.cfg`:

```
ck.config/config-service
```

Add the dependency in your serivice and call the `get-in-config` method where needed.

```clojure
(defservice
  my-service
  [[:EnvironmentConfigService register-hanlder!]]
  (init [this context]
    ...
    (get-in-config [:some :config :keys])
  ...))
```

`get-in-config` works similar to what is provided by TrapperKeeper's [`:ConfigService`](https://github.com/puppetlabs/trapperkeeper/blob/master/documentation/Built-in-Configuration-Service.md) but adds support for
environment variables. Any configuration string value prefixed with `"ENV_"` will be treated as an environment
variable and resolved once the service initializes

## License

Copyright © 2016 Jason Murphy

Distributed under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html).
