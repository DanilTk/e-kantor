Task:

Celem zadania jest przygotowanie aplikacji serwującej API REST, która pozwoli na
założenie konta oraz wymianę waluty w parze PLN<->USD.
Założenia funkcjonalne:

1. Aplikacja posiada REST API pozwalające na założenie konta walutowego.
2. Przy zakładaniu konta wymagane jest podanie początkowego salda konta w PLN.
3. Aplikacja przy zakładaniu konta wymaga od użytkownika podania imienia i nazwiska.
4. Aplikacja przy zakładaniu konta generuje identyfikator konta który powinien być
   używany przy wywoływaniu dalszych metod API.
5. Aplikacja powinna udostępnić REST API do wymiany pieniędzy w parze PLN<->USD
   (czyli PLN na USD oraz USD na PLN), a aktualny kurs wymiany pobrać z
   publicznego API NBP (http://api.nbp.pl/).
6. Aplikacja powinna udostępnić REST API do pobrania danych o koncie i jego
   aktualnego stanu w PLN i USD.
   Założenia niefunkcjonalne:
1. Aplikacja musi zostać wykonana w Kotlinie lub Javie.
2. Aplikacja może być wykonana w dowolnym frameworku.
3. Aplikacja nie musi zachowywać danych po restarcie.
4. Kod źródłowy aplikacji powinien zostać udostępniony na wybranym portalu do
   hostowania kodu (np. Gitlab, Github, Bitbucket).
5. Aplikacja musi być budowana przy pomocy narzędzia do budowania aplikacji (np.
   Maven, Gradle).
6. W przypadku niesprecyzowania czegoś w zadaniu - pozostaje dowolność.
7. W przypadku pytań – można się dopytywać mailowo.
   Oprócz spełnienia wymagań funkcjonalnych oceniany będzie również styl rozwiązania

ASSUMPTIONS:

1. Accounts do not have uniqueness constraints on name and surname.
2. No authentication is required.