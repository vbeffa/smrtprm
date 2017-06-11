# smrtprm

RSA message signer, implemented in Java using Maven as the build tool.

To sign a message from the command line, use the rsa_signer.sh script:

```bash
./rsa_signer.sh "message to sign"
```

Implementation notes:

- In order to promote immutability, all methods are static, making classes stateless (except for the static constants
  in `RsaSignerApp`).
- The private key file is written with 600 permissions (i.e., `-rw-------`).
- Max message length can be configured using the `max_message_len` property.
- Key size can be configured using the `key_size` property.

CI:

- [CircleCI](https://circleci.com/gh/vbeffa/smrtprm/tree/master)
