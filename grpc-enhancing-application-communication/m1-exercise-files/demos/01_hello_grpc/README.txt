In order to run this follow these steps:

1) In the nodejs folder, run "npm install" to install the gRPC dependencies for the demo
2) Run
  - go -u get google.golang.org/grpc
  - go get -u github.com/golang/protobuf/proto
  - go get -u github.com/golang/protobuf/protoc-gen-go
  - golang.org/x/net/context
3) Download the latest protocol buffer compiler from here: https://github.com/google/protobuf/releases
4) Generate the Go source code with the following command:
 - on Windows
   - protoc pb\messages.proto --go_out=plugins=grpc:go\src
 - on other systems
   - protoc pb/messages.proto --go_out=plugins=grpc:go/src
 - make sure that the protoc application is available on the path and run this from the demo's root directory
4) Build the go server (go build github.com/ps/hellogrpc)
5) Run the generated executable
6) Start the node client by running "node nodejs/app.js" from the demo's root directory
