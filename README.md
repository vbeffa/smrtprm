# smrtprm

RSA message signer, implemented in Java using Maven as the build tool.

To sign a message from the command line, use the rsa_signer.sh script:

```bash
./rsa_signer.sh message_to_sign
```

Implementation notes:

- The private key file is written with 600 permissions (i.e., -rw-------).
