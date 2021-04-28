# Maven configuration

##### Dependency
```
    <dependency>
            <groupId>com.github.vladimirantin</groupId>
            <artifactId>spring-core</artifactId>
            <version>1.3</version>
            <scope>provided</scope>
    </dependency>
```
##### Processor

```
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                    <annotationProcessorPaths>
                       <path>
                            <groupId>com.github.vladimirantin</groupId>
                            <artifactId>spring-core</artifactId>
                            <version>1.3</version>
                        </path>
                    </annotationProcessorPaths>
                </configuration>
            </plugin>
          </path>
        </plugins>
    </build>
```

## Docs

- [Usage](https://vladimirantin.github.io/projects/spring-core)