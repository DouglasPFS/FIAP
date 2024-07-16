#!/bin/bash

awslocal sqs create-queue --queue-name envia-emails

awslocal sqs create-queue --queue-name processa-pedidos

awslocal sqs create-queue --queue-name processa-entregas


