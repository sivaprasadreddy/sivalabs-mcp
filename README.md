# SivaLabs MCP
A demo application to explore MCP(Model Context Protocol) using Spring AI.

* **sivalabs-mcp-server-stdio**: MCP server using STDIO protocol that exposes videos information from [SivaLabs YouTube channel](https://www.youtube.com/sivalabs)
* * **sivalabs-mcp-server-webmvc**: MCP server using SSE or Stateless or Streamable HTTP protocol that exposes videos information from [SivaLabs YouTube channel](https://www.youtube.com/sivalabs)
* **sivalabs-mcp-client**: MCP Client web application that user can use to query information about SivaLabs channel videos using **sivalabs-mcp-server-stdio** MCP server with STDIO protocol.

## How to run?

```shell
$ git clone https://github.com/sivaprasadreddy/sivalabs-mcp.git
$ cd sivalabs-mcp
$ mvn clean package
```

### Test MCP Server using MCP Inspector

```shell
$ npx @modelcontextprotocol/inspector@latest
```

**Using java -jar command to run MCP server.**

```shell
Transport Type: STDIO
Command: java
Arguments: -jar /Users/siva/Siva/Projects/My/sivalabs-mcp/sivalabs-mcp-server-stdio/target/sivalabs-mcp-server-stdio-0.0.1.jar
```

**Using Docker to run MCP server.**

```shell
Transport Type: STDIO
Command: docker
Arguments: run -i --rm sivaprasadreddy/youtube-mcp-stdio
```

### Run MCP Client

* Create OpenAI key and set it as `OPENAI_API_KEY` environment variable.
* Update java and jar path in `sivalabs-mcp-client/src/main/resources/mcp-servers.json`

```shell
$ cd sivalabs-mcp-client-stdio
$ mvn spring-boot:run
```

Open http://localhost:8080

Try asking the following questions:

* What are the videos offered by SivaLabs?
* Give me the list of videos on IntelliJ from SivaLabs?
* Get more info on 'Spring Boot Microservices Complete Tutorial' video from SivaLabs?
