#!/bin/bash

if [ "$#" -ne 1 ]; then
    echo "Usage: ./rsa_signer message_to_sign"
    exit 1
fi

java -cp target/classes com.vbeffa.smrtprm.RsaSignerApp "$1"
