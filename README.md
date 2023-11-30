# SMTP Prankster

Authors :
- Rémi Ançay
- Lucas Charbonnier

## Project description
SMTP Prankster is, as the name implies, a SMTP client that sends prank emails. It was created in the context of an exercise during the DAI course at HEIG.
The prankster takes a list of messages, adresses and a number of groups. It then generates random groups of varying size. For each group, an email with a random message from the list will be sent. The first member of each group will be the "sender" of the email and the rest will be the recipients.

## Setup
In order to run the prankster you need a **mock** SMTP server ([maildev/maildev](https://github.com/maildev/maildev) for example). The prankster is not meant to be use for real mail pranks so make sure you run the program on a mock SMTP server and not a real server.

**Important**: In order for UTF8 characters to show in the maildev client, you need to change the view from Text to Source in the top panel. (special characters are not encoded correctly, as shown in this Github issue : https://github.com/maildev/maildev/issues/203).

To run the docker image for **maildev**, open a terminal and run :
>```docker run -d -p 1080:1080 -p 1025:1025 maildev/maildev```

You also need to add google.code.gson in the project libraries. To do this, go to File -> Project Structure -> Project settings -> Libraries. Now click the '+' button and choose "From maven..." and search for "gson" in the search bar. We used version 2.10.1 for the project.

### victims.txt format

The ```victims.txt``` file contains all the adresses that will be used in the prank emails. Every adress is separated by a newline, like so :

```
adress1@domain.com
adress2@domain.com
adress3@domain.com
```

### messages.json format

The ```messages.json``` file contains the prank messages. The messages are stored in a `messages` JSON array of objects. Each object contains 2 fields : ```subject``` and ```body```. Here's a valid example of messages.json :

```
{
  "messages": [
    {
      "subject": "subject 1",
      "body": "message 1"
    },
    {
      "subject": "subject 2",
      "body": "message 2"
    },
    {
      "subject": "subject 3",
      "body": "message 3"
    }
  ]
}
```

## Running the prankster

In order for the program to run, you need to provide 3 arguments :
- The path to the txt file containing the victims adresses
- The path to the JSON file containing the messages
- The number of groups 

To run the project with the files provided in the repo, you need to add the following arguments to the Run/Debug configuration :

```code/dai-lab-smtp/untitled/src/victims.txt code/dai-lab-smtp/untitled/src/messages.json 5```

The default content of these 2 files has been generated using ChatGPT 3.5.

## Implementation

The following is the UML diagram of our project :
![](figures/prankster-uml.JPG)

### MessagesList and EmailList
These two classes are responsible for reading the files given as arguments to the program. Each class knows how to "decode" their specific format.

They also provide an easy-to-use interface to, for example, get a certain amount of random adresses or a random message.

### Email and EmailBuilder
The Email class is very simple, it simply contains a bunch of attributes that represent an Email (sender, recipients, subject and body).

The EmailBuilder class is responsible for building Email objects. It uses EmailList and MessagesList to get the necessary data to build the Emails. The EmailBuilder class also contains the logic used to create random emails and random groups.

### RequestBuilder
The RequestBuilder is responsible for building SMTP requests from Email objects. SMTP requests are represented as an array of String objects.

### SMTPClient
The SMTPClient contains the client-server communication logic. It is able to send SMTP requests. Here's an example of a successful SMTP dialog between the prankster and maildev :

```
ehlo heig-vd.ch
220 dbc5c4e3e00f ESMTP
250-dbc5c4e3e00f Nice to meet you, [172.17.0.1]
250-PIPELINING
250-8BITMIME
mail from :<chahuteur.cheval@example.mobi>
250 Accepted
rcpt to :<loufoque.lapin@example.nom>
250 Accepted
rcpt to :<farfadet.facetieux@example.com>
250 Accepted
rcpt to :<frimousse.farceur@example.org>
250 Accepted
data
354 End data with <CR><LF>.<CR><LF>
From: chahuteur.cheval@example.mobi

Subject: Perdez du poids rapidement !


Découvrez le secret pour perdre du poids rapidement et facilement. Aucun exercice requis ! Nos pilules miraculeuses vous aideront à atteindre la silhouette de vos rêves en un rien de temps. Commandez maintenant et obtenez une remise exclusive.

.

250 Message queued as p4JqvAXl
quit
221 Bye
```

And here's what maildev receives (not the same example as the dialog shown above) :

![](figures/maildev.JPG)

### PranksterProgram
This class is the core of the prankster. It links EmailBuilder, RequestBuilder and SMTPClient in order to send the prank emails. It contains one public method `runOnce()` that is used to run the prank process one time.