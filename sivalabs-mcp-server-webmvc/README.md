# SivaLabs YouTube MCP WebMvc Server

```shell
$ mvn clean package
$ docker build -t sivaprasadreddy/youtube-mcp-webmvc .
```

### Test MCP Server using MCP Inspector

```shell
$ cd sivalabs-mcp-server-webmvc
$ mvn spring-boot:run
$ npx @modelcontextprotocol/inspector@latest
```

```shell
Transport Type: Streamable HTTP
URL: http://localhost:8080/mcp
```
