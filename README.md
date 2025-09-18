# SivaLabs MCP
A demo application to explore MCP(Model Context Protocol) using Spring AI.

* **sivalabs-mcp-server**: MCP server that exposes videos information from [SivaLabs YouTube channel](https://www.youtube.com/sivalabs)
* **sivalabs-mcp-client**: MCP Client web application that user can use to query information about SivaLabs channel videos

## How to run?

```shell
$ git clone https://github.com/sivaprasadreddy/sivalabs-mcp.git
$ cd sivalabs-mcp
$ mvn clean package
```

* Create OpenAI key and set it as `OPENAI_API_KEY` environment variable.
* Update java and jar path in `sivalabs-mcp-client/src/main/resources/mcp-servers.json`

```shell
$ cd sivalabs-mcp-client-stdio
$ mvn spring-boot:run
```

Open http://localhost:8080

Try asking the following questions:

* What are the videos offered by SivaLabs?
* Give me the list of videos on Spring from SivaLabs?
* Get more info on 'Spring Boot Microservices Complete Tutorial' video from SivaLabs?
