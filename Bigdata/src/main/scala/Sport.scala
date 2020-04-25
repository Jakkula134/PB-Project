
import org.apache.spark.{SparkConf, SparkContext}

// For implicit conversions from RDDs to DataFrames

object Sport {
  def main(args: Array[String]) {

    val sparkConf = new SparkConf().setAppName("SparkWordCount").setMaster("local[*]")

    val sc = new SparkContext(sparkConf)

    // Contains SQLContext which is necessary to execute SQL queries
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)

    // Reads json file and stores in a variable
    val tweet = sqlContext.read.json("C:\\Users\\Koppu\\PycharmProjects\\sample\\Sports_Tweets.json")

    //To register tweets data as a table
    tweet.createOrReplaceTempView("tweets")

    val disCat = sqlContext.sql("SELECT user.name as UserName,user.location as loc,text,created_at," +
      "CASE WHEN text like '%cricket%' THEN 'CRICKET'" +
      "WHEN text like '%Soccer%' THEN 'SOCCER'" +
      "WHEN text like '%Basketball%' THEN 'BASKET BALL'" +
      "WHEN text like '%Baseball%' THEN 'BASE BALL'" +
      "WHEN text LIKE '%Volleyball%' THEN 'VOLLEY BALL'" +
      "WHEN text like '%FieldHockey%' THEN 'FIELD HOCKEY'" +
      "WHEN text like '%AmericanFootball%' THEN 'AMERICAN FOOTBALL'" +
      "WHEN text like '%IceHockey%' THEN 'ICE HOCKEY'" +
      "WHEN text like '%rugby%' THEN 'RUGBY'" +
      "WHEN text like '%tennis%' THEN 'TENNIS'" +
      "WHEN text like '%badminton%' THEN 'BADMINTON'" +
      "END AS sportType from tweets where text is not null")

    disCat.createOrReplaceTempView("disCat2")

    println("Enter any one of the following query to get data")
    println("1.Query-1:Which Sport has more tweets")
    println("2.Query-2:Which user tweeted more on which sport")
    println("3.Query-3:Which country tweeted more on sports")
    println("4.Query-4:On which day more tweets are done")
    println("5.Query-5:Top followers")
    println("6.Query-6:On Which hours More Tweets Were Done")
    println("7.Query-7:Sensitive Content Statistics ")
    println("8.Query-8:Account verification Tweets")
    println("9.Query-9:Top Tweet text and Retweet count")
    println("10.Query-10:Users created per year")
    println("Enter any one of the following query to get data:")
    val count = scala.io.StdIn.readLine()
    count match {
      case "1" =>
        /*--------------------Query 1: This query fetches the sports and its popularity based on tweets-----------------------*/
        val textFile = sc.textFile("C:\\Users\\Koppu\\PycharmProjects\\sample\\Sports_Tweets.json")
        val cricket = (textFile.filter(line => line.contains("#cricket")).count())
        val Soccer = (textFile.filter(line => line.contains("#Soccer")).count())
        val Basketball = (textFile.filter(line => line.contains("#Basketball")).count())
        val Baseball = (textFile.filter(line => line.contains("#Baseball")).count())
        val Volleyball = (textFile.filter(line => line.contains("#Volleyball")).count())
        val FieldHockey = (textFile.filter(line => line.contains("#FieldHockey")).count())
        val AmericanFootball = (textFile.filter(line => line.contains("#AmericanFootball")).count())
        val IceHockey = (textFile.filter(line => line.contains("#IceHockey")).count())
        val rugby = (textFile.filter(line => line.contains("#rugby")).count())
        val tennis = (textFile.filter(line => line.contains("#tennis")).count())
        val badminton = (textFile.filter(line => line.contains("#badminton")).count())

        println("********************************************")
        println("Number of users tweeted on different sports")
        println("********************************************")
        println("cricket : %s".format(cricket))
        println("Soccer : %s".format(Soccer))
        println("Basketball : %s".format(Basketball))
        println("Baseball : %s".format(Baseball))
        println("Volleyball : %s".format(Volleyball))
        println("FieldHockey : %s".format(FieldHockey))
        println("AmericanFootball : %s".format(AmericanFootball))
        println("IceHockey : %s".format(IceHockey))
        println("rugby : %s".format(rugby))
        println("tennis : %s".format(tennis))
        println("badminton : %s".format(badminton))

      /*-----------------------------Query 2:  User who tweeted most on which Sport--------------------------------------------*/
      case "2" =>

        val r1 = sqlContext.sql("SELECT UserName,'CRICKET' as sportType,count(*) as count FROM disCat2 WHERE sportType='CRICKET' " +
          "group by UserName order by count desc limit 1")
        val r2 = sqlContext.sql("SELECT UserName,'SOCCER' as sportType,count(*) as count FROM disCat2 WHERE sportType='SOCCER' " +
          "group by UserName order by count desc limit 1 ")
        val r3 = sqlContext.sql("SELECT UserName,'BASKET BALL' as sportType,count(*) as count FROM disCat2 WHERE sportType='BASKET BALL' " +
          "group by UserName order by count desc limit 1 ")
        val r4 = sqlContext.sql("SELECT UserName,'BASE BALL' as sportType,count(*) as count FROM disCat2 WHERE sportType='BASE BALL' " +
          "group by UserName order by count desc limit 1 ")
        val r5 = sqlContext.sql("SELECT UserName,'VOLLEY BALL' as sportType,count(*) as count FROM disCat2 WHERE sportType='VOLLEY BALL' " +
          "group by UserName order by count desc limit 1 ")
        val r6 = sqlContext.sql("SELECT UserName,'FIELD HOCKEY' as sportType,count(*) as count FROM disCat2 WHERE sportType='FIELD HOCKEY' " +
          "group by UserName order by count desc limit 1 ")
        val r7 = sqlContext.sql("SELECT UserName,'AMERICAN FOOTBALL' as sportType,count(*) as count FROM disCat2 WHERE sportType='AMERICAN FOOTBALL' " +
          "group by UserName order by count desc limit 1 ")
        val r8 = sqlContext.sql("SELECT UserName,'ICE HOCKEY' as sportType,count(*) as count FROM disCat2 WHERE sportType='ICE HOCKEY' " +
          "group by UserName order by count desc limit 1 ")
        val r9 = sqlContext.sql("SELECT UserName,'RUGBY' as sportType,count(*) as count FROM disCat2 WHERE sportType='RUGBY' " +
          "group by UserName order by count desc limit 1")
        val r10 = sqlContext.sql("SELECT UserName,'TENNIS' as sportType,count(*) as count FROM disCat2 WHERE sportType='TENNIS' " +
          "group by UserName order by count desc limit 1")
        val r11 = sqlContext.sql("SELECT UserName,'BADMINTON' as sportType,count(*) as count FROM disCat2 WHERE sportType='BADMINTON' " +
          "group by UserName order by count desc limit 1 ")

        val rdd1 = r1.union(r2).union(r3).union(r4).union(r5).union(r6).union(r7).union(r8).union(r9). union (r10).union(r11)

        println("****************************************")
        println("Which user tweeted more on which Sport")
        println("****************************************")
        rdd1.show()

      /*-----------------------------------Query 3: Countries with more popular Sports-------------------------------------*/
      case "3" =>
        val stateWiseCnt = sqlContext.sql(
          """ SELECT Case
            |when user.location LIKE '%USA%' then 'United States'
            |when user.location LIKE '%India%' then 'India'
            |when user.location LIKE '%Germany%' then 'Germany'
            |when user.location LIKE '%Pakistan%' then 'Pakistan'
            |when user.location LIKE '%Australia%' then 'Australia'
            |when user.location LIKE '%France%' then 'France'
            |when user.location LIKE '%United Kingdom%' then 'United Kingdom'
            |when user.location LIKE '%Canada%' then 'Canada'
            |when user.location LIKE '%Spain%' then 'Spain'
            |when user.location LIKE '%Indonesia%' then 'Indonesia'
            |when user.location LIKE '%Mexico%' then 'Mexico'
            |when user.location LIKE '%Cameroon%' then 'Cameroon'
            |when user.location LIKE '%Argentina%' then 'Argentina'
            |when user.location LIKE '%South Africa%' then 'South Africa'
            |when user.location LIKE '%Nigeria%' then 'Nigeria'
            |when user.location LIKE '%Colombia%' then 'Colombia'
            |when user.location LIKE '%Malaysia%' then 'Malaysia'
            |when user.location LIKE '%Brazil%' then 'Brazil'
            |when user.location LIKE '%Philippines%' then 'Philippines'
            |when user.location LIKE '%Austria%' then 'Austria'
            |when user.location LIKE '%Venezuela%' then 'venezuela'
            |when user.location LIKE '%Netherlands%' then 'Netherlands'
            | end as US_State,text from tweets where text is not null""".stripMargin)
        stateWiseCnt.createOrReplaceTempView("stateWiseDataCnt")

        val stateWiseDataCnt = sqlContext.sql("select US_State, count(text) as State_Tweet_Count " +
            "from stateWiseDataCnt where US_State is not null " +
            "and text is not null group by US_State,text order by count(text) desc")

        println("*****************************************")
        println("Which country Tweeted More On Which Sport")
        println("*****************************************")
        stateWiseDataCnt.show();
      /*-------------------------------Query 4 : On which Day More Tweets are done-----------------------------------*/
      case "4" =>
        val day_data = sqlContext.sql("SELECT substring(user.created_at,1,3) as day from tweets where text is not null")

        day_data.createOrReplaceTempView("day_data")

        val days_final = sqlContext.sql(
          """ SELECT Case
            |when day LIKE '%Mon%' then 'MONDAY'
            |when day LIKE '%Tue%' then 'TUESDAY'
            |when day LIKE '%Wed%' then 'WEDNESDAY'
            |when day LIKE '%Thu%' then 'THURSDAY'
            |when day LIKE '%Fri%' then 'FRIDAY'
            |when day LIKE '%Sat%' then 'SATURDAY'
            |when day LIKE '%Sun%' then 'SUNDAY'
            | else
            | null
            | end as day1 from day_data where day is not null""".stripMargin)

        days_final.createOrReplaceTempView("days_final")

        val res = sqlContext.sql("SELECT day1 as Day,Count(*) as Day_Count from days_final where day1 is not null group by day1 order by count(*) desc")

        println ("**********************************")
        println("On Which Day More Tweets Were Done")
        println("**********************************")
        res.show()


      /*-----------------------Query 5: Top followers----------------------------------*/
      case "5" =>
        val df = sqlContext.read.json(
          "C:\\Users\\Koppu\\PycharmProjects\\sample\\Sports_Tweets.json")
        df.createOrReplaceTempView("tweets")
        val query = sqlContext.sql(
          "select user.name,user.followers_count from tweets order by user.followers_count desc limit 10")
        println("************************************************")
        println("Top followers")
        println("************************************************")
        query.show()

        /*-----------------------Query 6: On Which hours More Tweets Were Done----------------------------------*/
      case "6" =>
        val timehour = sqlContext.sql("SELECT SUBSTRING(created_at,12,2) as hour from tweets where text is not null")

        timehour.createOrReplaceTempView("timehour")

        val timeAnalysis=sqlContext.sql(""" SELECT Case
            |when hour>=0 and hour <4 then 'midnight'
            |when hour>=4 and hour <7 then 'early Morning'
            |when hour>=7 and hour <12 then 'Morning'
            |when hour>=12 and hour <15 then 'afternoon'
            |when hour>=15 and hour <18 then 'evening'
            |when hour>=18 and hour <=23 then 'night'
            end as time from timehour""".stripMargin)

        timeAnalysis.createOrReplaceTempView("timeAnalysis")

        val res = sqlContext.sql("SELECT time as hour,Count(*) as tweets_count from timeAnalysis where time is not null group by time order by count(*) desc")

        println ("**********************************")
        println("On Which hours More Tweets Were Done")
        println("**********************************")
        res.show()

      /*-----------------------------------Query 7 Sensitive Content Statistics  -------------------------------------*/
      case "7" =>
        val tweetcount=sqlContext.sql("select possibly_sensitive,count(*) from tweets where possibly_sensitive = 'true' or possibly_sensitive = 'false' " +
          "group by possibly_sensitive ")
        tweetcount.createOrReplaceTempView("tweetcount")
        println("****************************************")
        println("Sensitive Content Statistics ")
        println("****************************************")
        tweetcount.show()
        /*-----------------------------Query 8: Account verification Tweets-----------------------------------------------*/
      case "8" =>
        val acctVerify=sqlContext.sql("SELECT distinct id, " +
          "CASE when user.verified LIKE '%true%' THEN 'VERIFIED ACCOUNT'"+
          "when user.verified LIKE '%false%' THEN 'NON-VERIFIED ACCOUNT'"+
          "END AS Verified from tweets where text is not null")
        acctVerify.createOrReplaceTempView("acctVerify")
        var acctVerifydata=sqlContext.sql("SELECT  Verified, Count(Verified) as Count from acctVerify where id is NOT NULL and Verified is not null " +
          "group by Verified order by Count DESC")

        println("************************************************")
        println("Account verification Tweets")
        println("************************************************")
        acctVerifydata.show()

        /*----------------------------Query 9: Top Tweet text and Retweet count----------------------------------------*/
      case "9" =>
        val SQLquery = sqlContext.sql("SELECT user.name as Name,retweeted_status.text AS Retweet_Text,retweeted_status.retweet_count AS " +
          "Retweet_Count FROM tweets WHERE retweeted_status.retweet_count IS NOT NULL ORDER BY retweeted_status.retweet_count DESC")

        println("************************************************")
        println("Top Tweet text and Retweet count")
        println("************************************************")
        SQLquery.show()

      /*----------------------------Query 10: Users created per year----------------------------------------*/
      case "10" =>
        val SQLquery = sqlContext.sql("SELECT substring(user.created_at,27,4) as year,count(*) from tweets where user.created_at is not null " +
          "group by substring(user.created_at,27,4) order by count(1) desc")

        println("************************************************")
        println("Users created per year")
        println("************************************************")
        SQLquery.show()
    }
  }
}

