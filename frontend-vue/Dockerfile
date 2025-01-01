# Use Node.js as the base image for Vue
FROM node:16

# Set the working directory inside the container
WORKDIR /app

# Copy the package.json and package-lock.json first
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of your application code
COPY . .

# Expose the Vue development server port
EXPOSE 8077

# Run the Vue development server
CMD ["npm", "run", "serve"]
