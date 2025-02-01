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
1. Nadat de pizza aan de bezorger is meegegeven krijgt de klant direct updates van de hartslag van de bezorger zodra deze meetdata beschikbaar is van de gepatenteerde en geïmplanteerde pizza.nu lifestyle chip.

Met 'direct' wordt niet perse instantaan bedoeld (het is niet gewenst om kwantumverstrengeling toe te passen). Lees hier 'binnen een enkele seconde'.

### Technische notitie van de architect

De software architect heeft vooronderzoek gedaan, en de technologie *WebSockets* zou het mogelijk moeten maken om deze real-time bidirectionele communicatie mogelijk te maken zonder vervelende dingen als http-polling.

### Story: Als klant wil ik direct een update krijgen als mijn pizza gebakken is zodat ik alvast de tafel kan gaan dekken

Dit is de eerste story in het nieuwe thema 'realtime pizza'. 

Acceptatiecriteria:
- Als de bakker een pizza als gebakken markeert is dit direct zichtbaar op het scherm van alle klanten.
- Er wordt op enig moment gebruik gemaakt van WebSockets.

## Onzekerheden

Er zijn naast de functionele eisen ook nog onzekerheden:

1. Werkt dit ook op de technologie 'stack' van pizza.nu? (Spring en React)
1. Hoe veel tijd (lees: geld) gaat het team nodig hebben om deze nieuwe tech te leren?

Deze onzekerheden maken het onpraktisch om in een planning sessie schattingen te kunnen maken over hoe veel tijd het team nodig gaat hebben om de nieuwe features te implementeren. Oftewel: Het werk kan nog niet gepland worden. Onderzoek is nodig.

# Context en functionaliteit

Pizza.nu heeft een bestaande applicatie waarmee een klant een pizza kan bestellen. Vervolgens gaat de pizzabakker er mee aan de gang en uiteindelijk komt er hopelijk een bezorger op tijd met een warme pizza bij de hongerige klant.

Op dit moment is de pagina nog in ontwikkeling. Er is een pagina met twee tabs, eentje voor de bakker en eentje voor de klant. Later gaat dit uiteraard nog met een router en authenticatie uit elkaar gehaald worden voor de twee type actoren, maar dat is voor de ontwikkelaars gesneden koek en geen groot risico. Voor nu is de huidige pagina voldoende. Voor het testen kan je twee browsers openen met op de ene  het klantscherm en de andere het bakker scherm.

## Kwaliteitsattributen

- Op dit moment is de applicatie geschreven voor enkele gelijktijdige gebruikers en een enkele pizzabakker. Dat is ok.
- Beveiliging van de applicatie is nog niet in scope. In deze fase van de ontwikkeling van pizza.nu is dit niet het grootste risico. Also: Security is saai en stom.
- Omdat snelheid belangrijk is voor pizza.nu is moeten wijzigingen binnen één seconde zichtbaar zijn voor andere gebruikers.

## Beperkingen

- Ter bevordering van de standaardisatie worden alle applicaties binnen pizza.nu ontwikkeld met Spring Boot (Java) en React (JavaScript).
- Taal in de code is engels. In de documentatie mag engels of nederlands.
- Gebruik van externe libraries en frameworks minimaliseren. Gebruik van externe libraries brengt vaak veel onbekende en onveilige code met zich mee. Vind aub het wiel opnieuw uit.

# Deployment

## Software

React
Java 17
Spring Boot
MySQL

### Building 

Back-end
`mvn package`

Front-end
`npm run build`

### Configuration

Back-end: Het .env bestand bevat poorten en gebruikersnaam/wachtwoord voor toegang tot de database. Deze worden automatisch gebruikt door docker compose. Sommigen variabelen zijn gedupliceerd in een .env bestand in de backend/src/main/resources/.env voor als je zonder docker compose gaat ontwikkelen.

Front-end: Geen configuratie.

## Operation and Support

Met docker:
```
docker compose up -d
```

frontend url is standaard http://localhost:3000/.

backend url is standaard http://localhost:8080/.

Generated Backend API Docs UI: http://localhost:8080/swagger-ui/index.html

Je kunt ook alleen onderdelen van de app met docker starten zie de compose file voor de namen van de componenten. Bijvoorbeeld als je de alleen ontwikkelwerk doet aan de frontend:

```
docker compose up -d frontend
docker compose up -d backend
docker compose up -d db
docker compose up -d db backend
```

De verschillende componenten gebruiken elk een eigen netwerk port.

| **component** | **port** |
| ------------- | -------- |
| db            | 3306     |
| backend       | 8080     |
| frontend      | 3000     |

### Back-end

Run de back-end met Maven vanuit de /backend directory:

`mvn spring-boot:run`

### Front-end

Run de front-end vanuit de /frontend directory.

`npm run start`

### DB

These values are configured in the .env file for docker compose.

| **username**  | **password** | **database** |
| ------------- | ------------ | ------------ |
| root          | pizza        | pizzanu      |