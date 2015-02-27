**Aihe:** Snake3D - Matopeli 3D-perspektiivistä

Tarkoituksena toteuttaa klassinen matopeli 3D-perspektiivistä. 
Madon liikkuminen tapahtuu kolmiulotteisessa kentässä - kuitenkin niin, ettei mato voi liikkua pystysuunnassa. Mato kasvaa 
sitä mukaan kun pelaaja kerää omenoita.
Peli päättyy, jos mato osuu itseensä tai liikkuu kentän ulkopuolelle.

Peli tulee käyttämään OpenGL-rajapintaa käyttävää LibGDX-grafiikkakirjastoa (http://libgdx.badlogicgames.com/)

**Käyttäjät:** Pelaaja

**Käyttäjien toiminnot:**
- Pelin aloittaminen
- Pelin keskeyttäminen
- Madon ohjaaminen
- Omenoiden kerääminen (kerryttää pisteitä)
- Madon osuessa itseensä tai liikkuessa pelikentän ulkopuolelle peli päättyy
- Pelin päätyttyä mahdollisuus pelata uudelleen tai lopettaa ohjelman suorittaminen
- Edellisten pelien pisteiden tarkastelu

**Rakennekuvaus**
Pelin pääluokka on Snake3D. Luokan sisällä luodaan näkymä- ja kuuntelijaoliot ja luokka huolehtii myös näkymien vaihtamisesta. 

Jokaiselle pelialueen objektille (omena, mato, kamera, pelikenttä) on oma luokka. Varsinainen peliluokka on GameLoopScene-näkymä, 
jossa em. luokkien oliot luodaan. Pistelistalle (ScoreBoard) on oma luokkansa. Pistelistaa varten on myös kolme erilaista 
tiedostonkäsittelyluokkaa.

Näkymäluokat laajentavat abstraktia Snake3DScene-luokkaa. Jokaiselle näkymälle (= *Scene-luokalle) on oma näppäimistönkuuntelijaluokka. Sen lisäksi yksi näppäimistönkuuntelija on voimassa koko ohjelman suorituksen ajan, tämän luokan avulla voidaan siirtyä ikkunoidusta tilasta koko näytön tilaan.

Lisäksi ohjelmassa on kolme ns. apuluokkaa, jotka ovat ScoreBoardItem, GameState ja SnakeDirection. ScoreBoardItem-olioita käytetään ScoreBoard-luokan pisteitä säilövässä taulukossa, GameState määrittelee pelitilat (eli käynnissä tai pysähtynyt) ja SnakeDirection määrittelee madon suunnan liikkuessa.
