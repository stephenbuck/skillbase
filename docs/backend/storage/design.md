**Design:**

The storage service is responsible for managing a filesystem for storing
skillbase artifacts.

The storage service is based on the JuiceFS filesystem, which provides a
POSIX-compliant interface on top of a variety of backends. Since one of the
requirements of the skillbase product is ease of integration into existing
systems (both on-premises and in the cloud), JuiceFS is an easy choice.

Although layering a filesystem on top of an object store introduces some
overhead, there are a number of advantages:

* Ease of integration with exist systems that use file systems
* Allows for organization into subdirectories.
* Allows for fine-grained access control.

Another advantage of JuiceFS is its synchronization capabilities, which
makes it easy to perform backups and migrations.

The initial design is to use POSIX directories to partition across the different skillbase services and within each service. Directories will have service-specific names and file names will be based on UUIDs. MIME types will be handled by file extensions.

File storage, for images and other artifacts, is based on a POSIX filesystem
provided by the JuiceFS filesystem. The storage service will use the following
directory layout. File names will be based on UUIDs and MIME types will be
handled by file extensions.

```
skillbase
    storage
        <file-uuid>.<file-extension>
        ...
```

See Backend / Design for common design elements.
