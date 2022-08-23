
<p align="center"> <img src="https://user-images.githubusercontent.com/108894931/186280464-52e5abae-1660-410c-9082-c94edfb18b38.jpg" alt="stock"/> 
  </a>
</p>

A  Clean architecture MVVM pet project that have two screens firs for listings of company's that we get from Rest API("https://www.alphavantage.co/") we get the CSV file and parse it by OpenCsv  showing user the basic information like full company name and company signature   because that list of 
company's too big I use ROOM database to store company names and the stock exchange short name locally for the first screen I also implement search to find a specific company, user can search by name or by stock signature (1 Screenshot).
 When user click on any company in the list we make another network call by Retrofit 2 and take information about specific company by passing company signature to request then we parse JSON response all requests and parsing execute in parallel by using kotlin coroutines we also handle all state's for viewmodel's.
 On second screen user have some information about company like name, stock signature, Industry, Country, short story about company and the market summary that draw by Canvas using the data from response.
 
 For this project I used: Kotlin,Clean architecture(MVVM), Jetpack compose, Kotlin Corutins, ROOM,Retrofit 2,Compose Destinations, Dagger Hilt for DI and library OpenCSV.
