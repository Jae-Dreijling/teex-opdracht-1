# Week 1 - Casus - Pizza.nu

## Inleiding

Ken je dat, je hebt een pizza besteld en zit met smart te wachten bij de voordeur met zo veel vragen !?

- Is mijn pizza al gebakken? Kan ik al de tafel gaan dekken?
- Hoe gaat het met de pizzabakker? 
- Wat is de locatie van de pizzabezorger? Hoe staat het met de snelheid? Wat is de hartslag?
- Hoe warm is de pizza eigenlijk? 

Om deze vragen te kunnen beantwoorden kan je natuurlijk bellen: 0800-PIZZANU voor antwoorden op al je vragen! Maarrr, vanaf volgende week krijgt de pizza.nu een nieuwe feature waarmee jij live updates kunt ontvangen en direct met jouw bakker en jouw bezorger kunt chatten. Motiverende speeches, geruststellende updates en live foto rapportage van jouw pizza is nu binnen bereik!

## Nieuwe feature(s): Realtime pizza

Pizza.nu heeft een bestaande applicatie waarmee een klant een pizza kan bestellen. Vervolgens gaat de pizzabakker er mee aan de gang en uiteindelijk komt er hopelijk een bezorger op tijd met een warme pizza bij de hongerige klant. Uit marktonderzoek blijkt nu dat klanten graag meer betrokken willen worden bij het pizza proces! Van bakken tot leveren, alles willen ze weten. 

De functionele eisen waar mee gewerkt kan worden zijn, in volgorde van prioriteit, de volgende:

1. Een klant die een pizza heeft besteld moet direct (binnen 1 seconde) een update krijgen als een pizza gebakken is.
1. Een klant kan na een bestelling direct vragen stellen aan de pizzabakker, en een antwoord van de bakker moet direct getoond worden aan de klant.
1. Een klant krijgt direct een bericht als de pizza aan de bezorger is meegegeven.
1. Nadat de pizza aan de bezorger is meegegeven krijgt de klant direct updates van de hartslag van de bezorger zodra deze meetdata beschikbaar is van de gepantenteerde en geimplanteerde pizza.nu lifestyle chip.

Met 'direct' wordt niet perse instantaan bedoeld (het is niet gewenst om kwantumverstrengeling toe te passen). Lees hier 'binnen een enkele seconde'.

### Technische notitie van de architect

De software architect heeft vooronderzoek gedaan, en de technologie *WebSockets* zou het mogelijk moeten maken om deze real-time bidirectionele communicatie mogelijk te maken zonder vervelende dingen als http-polling.

### Story: Als klant wil ik direct een update krijgen als mijn pizza gebakken is zodat ik alvast de tafel kan gaan dekken

Dit is de eerste story in het nieuwe thema 'realtime pizza'. 

Voorgestelde applicatie aanpassingen:
- Het klanten-FrontEnd wordt aangepast met een statusicoon per pizza of deze gebakken is.
- In de bakker FrontEnd komt per pizza een knop om aan te geven dat een pizza gebakken is.
- Als er op de bakker knop gedrukt wordt dan gaat er direct een bericht naar de backend dat de pizza gebakken is.
- De database is aangepast zodat er per bestelde pizza opgeslagen kan worden wat de status van de pizza is. De standaard waarde is 'bestelling ontvangen'.
- De BackEnd reageert direct op het bericht van de bakker door de pizza status in de database aan te passen naar 'gebakken'.

Acceptatiecriteria:
- Als de bakker een pizza als gebakken markeert is dit binnen 1 seconde zichtbaar op het scherm van de klant.
- Er wordt op enig moment gebruik gemaakt van WebSockets.

## Onzekerheden

TODO: Gaan we deze al benoemen en geven aan studenten of gaan we die zelf in Les 1 naar boven halen?

Er zijn naast de functionelen eisen ook nog onzekerheden:

1. Werkt dit ook op de technologie 'stack' van pizza.nu? (Spring en React)
1. Hoe veel tijd (lees: geld) gaat het team nodig hebben om deze nieuwe tech te leren?

Deze onzekerheden maken het onpraktisch om in een planning sessie schattingen te kunnen maken over hoe veel tijd het team nodig gaat hebben om de nieuwe features te implementeren. Oftewel: Het werk kan nog niet gepland worden. Samen met het team is een nieuw soort taak op het scrum bord van het team geplaatst: Een [SPIKE](https://www.google.com/search?q=what+is+a+spike+in+scrum). Het doel van een spike is om deze onzekerheden te tacklen zonder de features volledig te moeten implementeren. De spike wordt ingepland met een [timebox](https://www.google.com/search?q=what+is+a+timebox+in+agile) van een week.