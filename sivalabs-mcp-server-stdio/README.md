# SivaLabs YouTube MCP STDIO Server

```shell
$ mvn clean package
$ docker build -t sivaprasadreddy/youtube-mcp-stdio .
```

In `~/Library/Application Support/Claude/claude_desktop_config.json` add the MCP Server:

```json
{
    "mcpServers": {
        "sivalabs-youtube": {
            "command": "docker",
            "args": [
                "run",
                "-i",
                "--rm",
                "sivaprasadreddy/youtube-mcp-stdio"
            ]
        }
    }
}
```

Now open Claude Desktop

Try asking the following questions:

* What are the videos offered by SivaLabs?
* Give me the list of videos on IntelliJ from SivaLabs?
* Get more info on 'Spring Boot Microservices Complete Tutorial' video from SivaLabs?
