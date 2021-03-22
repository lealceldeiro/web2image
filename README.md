# web2image
Java Application to take screenshots from the content loaded from a URL

Run:

- `mvn clean package`
- `java -jar target/web2image-1.0-SNAPSHOT-jar-with-dependencies.jar "<url_1>" "<url_1>" "<url_N>" "<output>" "<driver_location>"`
  
    where:
    + `url_1`, `url_2`, ..., `url_N` can be any url you want to take a screenshot from (you can specify any number of arguments here)
    + `outpout` is the file system directory where the images will be stored
    + `driver_location` is the location on the file system where the driver is located. Up to now the following drivers ares supported:
      * *geckodriver*
