# Week 1 - Casus - Pizza.nu

## Inleiding

Ken je dat, je hebt een pizza besteld en zit met smart te wachten bij de voordeur met zo veel vragen !?

- Wanneer komt mijn pizza?
- Hoe gaat het met de pizzabakker? 
- Wat is de locatie van de pizzabezorger? Hoe staat het met de snelheid? Wat is de hartslag?
- Hoe warm is de pizza eigenlijk? 

Om deze vragen te kunnen beantwoorden kan je natuurlijk bellen: 0800-PIZZANU voor antwoorden op al je vragen! Maarrr, vanaf volgende week krijgt de pizza.nu een nieuwe feature waarmee jij live updates kunt ontvangen en direct met jouw bakker en jouw bezorger kunt chatten. Motiverende speeches, geruststellende updates en live foto rapportage van jouw pizza is nu binnen bereik!

## Realtime pizza

Pizza.nu heeft een bestaande applicatie waarmee een klant een pizza kan bestellen. Vervolgens gaat de pizzabakker er mee aan de gang en uiteindelijk komt er hopelijk een bezorger op tijd met een warme pizza bij de hongerige klant. Uit marktonderzoek blijkt nu dat klanten graag meer betrokken willen worden bij het pizza proces! Van bakken tot leveren, alles willen ze weten. 

De software architect heeft vooronderzoek gedaan, en met gebruik van WebSockets moet het mogelijk zijn om deze real-time bidirectionele communicatie mogelijk te maken. De functionele eisen waar mee gewerkt kan worden zijn, in volgorde van prioriteit, de volgende:

1. Een klant die een pizza heeft besteld moet direct (binnen 1 seconde) een update krijgen als een pizza gebakken is.
1. Een klant kan na een bestelling direct vragen stellen aan de pizzabakker, en een antwoord van de bakker moet direct getoond worden aan de klant.
1. Een klant krijgt direct een bericht als de pizza aan de bezorger is meegegeven.
1. Nadat de pizza aan de bezorger is meegegeven krijgt de klant direct updates van de hartslag van de bezorger zodra deze meetdata beschikbaar is van de gepantenteerde en geimplanteerde pizza.nu lifestyle chip.

Met 'direct' wordt niet perse instantaan bedoeld (het is niet nodig kwantumverstrengeling toe te passen). Lees hier 'binnen een enkele seconde'.
