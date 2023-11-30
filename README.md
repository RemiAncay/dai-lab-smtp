# SMTP Prankster

Authors :
- Rémi Ançay
- Lucas Charbonnier

## Project description
SMTP Prankster is, as the name implies, a SMTP client that sends prank emails. It was created in the context of an exercise during the DAI course at HEIG.
The prankster takes a list of messages, adresses and a number of groups. It then generates random groups of varying size. For each group, an email with a random message from the list will be sent. The first member of each group will be the "sender" of the email and the rest will be the recipients.

## Setup
In order to run the prankster you need a **mock** SMTP server ([maildev/maildev](https://github.com/maildev/maildev) for example). The prankster is not meant to be use for real mail pranks so make sure you run the program on a mock SMTP server and not a real server.

To run the docker image for **maildev**, open a terminal and run :
>```docker run -d -p 1080:1080 -p 1025:1025 maildev/maildev```

### Message

## How to use
- todo parler des arguments du programme

## Implementation