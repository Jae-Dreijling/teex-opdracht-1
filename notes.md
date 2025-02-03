# Begrippen en Beschrijvingen - WebSockets en Gerelateerde Technieken

## 1. Stateless Protocol (Stateloos Protocol)
Een stateloos protocol slaat geen informatie op over eerdere communicatie tussen client en server. Elke aanvraag wordt onafhankelijk behandeld zonder kennis van eerdere verzoeken.  
**Voorbeeld:** HTTP is een stateloos protocol.

## 2. Stateful Protocol (Met Status Protocol)
Een protocol dat een actieve verbinding behoudt tussen client en server en gegevens onthoudt tijdens een sessie.  
**Voorbeeld:** WebSockets.

## 3. HTTP vs WebSockets
- **HTTP:**  
  Een stateloos, unidirectioneel protocol waarin de client een verzoek stuurt en de server een reactie geeft. Elke communicatie opent en sluit een aparte verbinding.
- **WebSockets:**  
  Een stateful, bidirectioneel protocol waarbij de verbinding open blijft totdat een van de partijen deze sluit. Communicatie kan in beide richtingen plaatsvinden zonder nieuwe verbindingen te openen.

## 4. Statuscode 101 (Switching Protocols)
Dit geeft aan dat een server akkoord gaat met het upgraden van het communicatieprotocol, bijvoorbeeld van HTTP naar WebSockets.

## 5. Long Polling vs Short Polling
- **Short Polling:**  
  De client stuurt regelmatig verzoeken naar de server om nieuwe gegevens op te halen, ongeacht of er een update beschikbaar is. Dit kan leiden tot een hoge netwerkbelasting.
- **Long Polling:**  
  De server houdt het verzoek van de client vast totdat er een update beschikbaar is, waarna de server de gegevens terugstuurt. Dit vermindert netwerkbelasting en is efficiënter voor real-time toepassingen.

## 6. Gebruikssituaties van WebSockets
- **Realtime Webapplicaties:** Voor toepassingen zoals financiële handelsplatformen waarbij gegevens continu moeten worden bijgewerkt.
- **Gaming:** Voor realtime gegevensuitwisseling zonder vertraging.
- **Chatapplicaties:** Voor het efficiënt uitwisselen van berichten zonder steeds nieuwe verbindingen te openen.

## 7. Wanneer geen WebSockets te gebruiken
Voor toepassingen waar alleen historische gegevens moeten worden opgehaald of waarbij een eenmalige gegevensoverdracht volstaat, zoals statische content op websites. HTTP is hier een betere keuze.

## 8. Polling
- **Short Polling:**  
  De server stuurt direct een lege of gevulde respons terug, ook wanneer er geen nieuwe gegevens beschikbaar zijn.
- **Long Polling:**  
  De server wacht met het versturen van een respons totdat er relevante gegevens beschikbaar zijn, wat zorgt voor efficiëntere netwerkbelasting.

## 9. Voorbeelden van toepassingen van Polling
- **Taxi Service Tracking:**  
  Het voortdurend controleren van de locatie van een chauffeur.
- **Trein Tracking:**  
  Het monitoren van de locatie van treinen in realtime.
